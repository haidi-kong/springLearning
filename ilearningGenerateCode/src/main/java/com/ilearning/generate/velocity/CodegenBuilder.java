package com.ilearning.generate.velocity;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import com.ilearning.common.mybatis.core.dataobject.BaseDO;
import com.ilearning.generate.dataObject.CodegenColumnDO;
import com.ilearning.generate.dataObject.CodegenTableDO;
import com.ilearning.generate.dataObject.SchemaColumnDO;
import com.ilearning.generate.dataObject.SchemaTableDO;
import com.ilearning.generate.enums.codegen.*;

import java.math.BigDecimal;
import java.util.*;

import static cn.hutool.core.text.CharSequenceUtil.*;

/**
 * 代码生成器的 Builder，负责：
 * 1. 将数据库的表 {@link SchemaTableDO} 定义，构建成 {@link CodegenTableDO}
 * 2. 将数据库的列 {@link SchemaColumnDO} 构定义，建成 {@link CodegenColumnDO}
 */

public class CodegenBuilder {

    /**
     * 字段名与 {@link CodegenColumnListConditionEnum} 的默认映射
     * 注意，字段的匹配以后缀的方式
     */
    private static final Map<String, CodegenColumnListConditionEnum> columnListOperationConditionMappings =
            MapUtil.<String, CodegenColumnListConditionEnum>builder()
                    .put("name", CodegenColumnListConditionEnum.LIKE)
                    .put("time", CodegenColumnListConditionEnum.BETWEEN)
                    .put("date", CodegenColumnListConditionEnum.BETWEEN)
                    .build();

    /**
     * 字段名与 {@link CodegenColumnHtmlTypeEnum} 的默认映射
     * 注意，字段的匹配以后缀的方式
     */
    private static final Map<String, CodegenColumnHtmlTypeEnum> columnHtmlTypeMappings =
            MapUtil.<String, CodegenColumnHtmlTypeEnum>builder()
                    .put("status", CodegenColumnHtmlTypeEnum.RADIO)
                    .put("sex", CodegenColumnHtmlTypeEnum.RADIO)
                    .put("type", CodegenColumnHtmlTypeEnum.SELECT)
                    .put("image", CodegenColumnHtmlTypeEnum.UPLOAD_IMAGE)
                    .put("file", CodegenColumnHtmlTypeEnum.UPLOAD_FILE)
                    .put("content", CodegenColumnHtmlTypeEnum.EDITOR)
                    .put("description", CodegenColumnHtmlTypeEnum.EDITOR)
                    .put("demo", CodegenColumnHtmlTypeEnum.EDITOR)
                    .put("time", CodegenColumnHtmlTypeEnum.DATETIME)
                    .put("date", CodegenColumnHtmlTypeEnum.DATETIME)
                    .build();

    /**
     * 多租户编号的字段名
     */
    public static final String TENANT_ID_FIELD = "tenantId";
    /**
     * {@link BaseDO} 的字段
     */
    public static final Set<String> BASE_DO_FIELDS = new HashSet<>();
    /**
     * 新增操作，不需要传递的字段
     */
    private static final Set<String> CREATE_OPERATION_EXCLUDE_COLUMN = Sets.newHashSet("id");
    /**
     * 修改操作，不需要传递的字段
     */
    private static final Set<String> UPDATE_OPERATION_EXCLUDE_COLUMN = Sets.newHashSet();
    /**
     * 列表操作的条件，不需要传递的字段
     */
    private static final Set<String> LIST_OPERATION_EXCLUDE_COLUMN = Sets.newHashSet("id");
    /**
     * 列表操作的结果，不需要返回的字段
     */
    private static final Set<String> LIST_OPERATION_RESULT_EXCLUDE_COLUMN = Sets.newHashSet();

    /**
     * Java 类型与 MySQL 类型的映射关系
     */
    private static final Map<String, Set<String>> javaTypeMappings = MapUtil.<String, Set<String>>builder()
            .put(Boolean.class.getSimpleName(), Sets.newHashSet("bit"))
            .put(Integer.class.getSimpleName(), Sets.newHashSet("tinyint", "smallint", "mediumint", "int"))
            .put(Long.class.getSimpleName(), Collections.singleton("bigint"))
            .put(Double.class.getSimpleName(), Sets.newHashSet("float", "double"))
            .put(BigDecimal.class.getSimpleName(), Sets.newHashSet("decimal", "numeric"))
            .put(String.class.getSimpleName(), Sets.newHashSet("tinytext", "text", "mediumtext", "longtext", // 长文本
                    "char", "varchar", "nvarchar", "varchar2")) // 短文本
            .put(Date.class.getSimpleName(), Sets.newHashSet("datetime", "time", "date", "timestamp"))
            .put("byte[]", Sets.newHashSet("blob"))
            .build();

    static {
        Arrays.stream(ReflectUtil.getFields(BaseDO.class)).forEach(field -> BASE_DO_FIELDS.add(field.getName()));
        BASE_DO_FIELDS.add(TENANT_ID_FIELD);
        // 处理 OPERATION 相关的字段
        CREATE_OPERATION_EXCLUDE_COLUMN.addAll(BASE_DO_FIELDS);
        UPDATE_OPERATION_EXCLUDE_COLUMN.addAll(BASE_DO_FIELDS);
        LIST_OPERATION_EXCLUDE_COLUMN.addAll(BASE_DO_FIELDS);
        LIST_OPERATION_EXCLUDE_COLUMN.remove("createTime"); // 创建时间，还是可能需要传递的
        LIST_OPERATION_RESULT_EXCLUDE_COLUMN.addAll(BASE_DO_FIELDS);
        LIST_OPERATION_RESULT_EXCLUDE_COLUMN.remove("createTime"); // 创建时间，还是需要返回的
    }

    public static CodegenTableDO buildTable(SchemaTableDO schemaTable) {
        CodegenTableDO table = convert(schemaTable);
        // 以 system_dept 举例子。moduleName 为 system、businessName 为 dept、className 为 SystemDept
        // 如果不希望 System 前缀，则可以手动在【代码生成 - 修改生成配置 - 基本信息】，将实体类名称改为 Dept 即可
        table.setModuleName(subBefore(table.getTableName(), '_', false)); // 第一个 _ 前缀的前面，作为 module 名字
        table.setBusinessName(toCamelCase(subAfter(table.getTableName(),
                '_', false))); // 第一步，第一个 _ 前缀的后面，作为 module 名字; 第二步，可能存在多个 _ 的情况，转换成驼峰
        table.setClassName(upperFirst(toCamelCase( // 驼峰 + 首字母大写
                subAfter(table.getTableName(), '_', false)))); // 第一个 _ 前缀的前面，作为 class 名字
        table.setClassComment(subBefore(table.getTableComment(), // 去除结尾的表，作为类描述
                '表', true));
        table.setTemplateType(CodegenTemplateTypeEnum.CRUD.getType());
        table.setScene(CodegenSceneEnum.APP.getScene()); // 不走security权限校验

        return table;
    }

    public static CodegenTableDO convert(SchemaTableDO bean) {
        if ( bean == null ) {
            return null;
        }

        CodegenTableDO codegenTableDO = new CodegenTableDO();

        codegenTableDO.setCreateTime( bean.getCreateTime() );
        codegenTableDO.setTableName( bean.getTableName() );
        codegenTableDO.setTableComment( bean.getTableComment() );

        return codegenTableDO;
    }


    public List<CodegenColumnDO> buildColumns(Long tableId, List<SchemaColumnDO> schemaColumns) {
        List<CodegenColumnDO> columns = convertList(schemaColumns);
        for (CodegenColumnDO column : columns) {
            column.setTableId(tableId);
            initColumnDefault(column);
        }
        return columns;
    }

    public List<CodegenColumnDO> convertList(List<SchemaColumnDO> list) {
        if ( list == null ) {
            return null;
        }

        List<CodegenColumnDO> list1 = new ArrayList<CodegenColumnDO>( list.size() );
        for ( SchemaColumnDO schemaColumnDO : list ) {
            list1.add( schemaColumnDOToCodegenColumnDO( schemaColumnDO ) );
        }

        return list1;
    }

    public static CodegenColumnDO schemaColumnDOToCodegenColumnDO(SchemaColumnDO schemaColumnDO) {
        if ( schemaColumnDO == null ) {
            return null;
        }

        CodegenColumnDO codegenColumnDO = new CodegenColumnDO();

        codegenColumnDO.setColumnName( schemaColumnDO.getColumnName() );
        codegenColumnDO.setColumnType( schemaColumnDO.getColumnType() );
        codegenColumnDO.setColumnComment( schemaColumnDO.getColumnComment() );
        codegenColumnDO.setNullable("YES".equals(schemaColumnDO.getIsNullable()) );
        codegenColumnDO.setPrimaryKey("PRI".equals(schemaColumnDO.getColumnKey()));
        codegenColumnDO.setAutoIncrement("auto_increment".equals(schemaColumnDO.getExtra()));
        codegenColumnDO.setOrdinalPosition( schemaColumnDO.getOrdinalPosition() );
        initColumnDefault(codegenColumnDO);
        return codegenColumnDO;
    }





    /**
     * 初始化 Column 列的默认字段
     *
     * @param column 列定义
     */
    private static void initColumnDefault(CodegenColumnDO column) {
        // 处理 Java 相关的字段的默认值
        processColumnJava(column);
        // 处理 CRUD 相关的字段的默认值
        processColumnOperation(column);
        // 处理 UI 相关的字段的默认值
        processColumnUI(column);
    }

    private static void processColumnJava(CodegenColumnDO column) {
        // 处理 javaField 字段
        column.setJavaField(toCamelCase(column.getColumnName()));
        // 处理 dictType 字段，暂无
        // 处理 javaType 字段(兼容无符号类型)
        String dbType = replaceIgnoreCase(subBefore(column.getColumnType(), '(', false),
                " UNSIGNED", "");
        javaTypeMappings.entrySet().stream()
                .filter(entry -> entry.getValue().contains(dbType))
                .findFirst().ifPresent(entry -> column.setJavaType(entry.getKey()));
        if (column.getJavaType() == null) {
            throw new IllegalStateException(String.format("column(%s) 的数据库类型(%s) 找不到匹配的 Java 类型",
                    column.getColumnName(), column.getColumnType()));
        }
    }

    private static void processColumnOperation(CodegenColumnDO column) {
        // 处理 createOperation 字段
        column.setCreateOperation(!CREATE_OPERATION_EXCLUDE_COLUMN.contains(column.getJavaField())
                && !column.getPrimaryKey()); // 对于主键，创建时无需传递
        // 处理 updateOperation 字段
        column.setUpdateOperation(!UPDATE_OPERATION_EXCLUDE_COLUMN.contains(column.getJavaField())
                || column.getPrimaryKey()); // 对于主键，更新时需要传递
        // 处理 listOperation 字段
        column.setListOperation(!LIST_OPERATION_EXCLUDE_COLUMN.contains(column.getJavaField())
                && !column.getPrimaryKey()); // 对于主键，列表过滤不需要传递
        // 处理 listOperationCondition 字段
        columnListOperationConditionMappings.entrySet().stream()
                .filter(entry -> StrUtil.endWithIgnoreCase(column.getJavaField(), entry.getKey()))
                .findFirst().ifPresent(entry -> column.setListOperationCondition(entry.getValue().getCondition()));
        if (column.getListOperationCondition() == null) {
            column.setListOperationCondition(CodegenColumnListConditionEnum.EQ.getCondition());
        }
        // 处理 listOperationResult 字段
        column.setListOperationResult(!LIST_OPERATION_RESULT_EXCLUDE_COLUMN.contains(column.getJavaField()));
    }

    private static void processColumnUI(CodegenColumnDO column) {
        // 基于后缀进行匹配
        columnHtmlTypeMappings.entrySet().stream()
                .filter(entry -> StrUtil.endWithIgnoreCase(column.getJavaField(), entry.getKey()))
                .findFirst().ifPresent(entry -> column.setHtmlType(entry.getValue().getType()));
        // 如果是 Boolean 类型时，设置为 radio 类型.
        // 其它类型，因为字段名可以相对保障，所以不进行处理。例如说 date 对应 datetime 类型.
        if (Boolean.class.getSimpleName().equals(column.getJavaType())) {
            column.setHtmlType(CodegenColumnHtmlTypeEnum.RADIO.getType());
        }
        // 兜底，设置默认为 input 类型
        if (column.getHtmlType() == null) {
            column.setHtmlType(CodegenColumnHtmlTypeEnum.INPUT.getType());
        }
    }

}
