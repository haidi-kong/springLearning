package com.ilearning.common.rabbitMq;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: CanalMQListener
 * @Description: Canal 接受实体
 * @Author: LZJ
 * @DATE: 2022/11/20 9:13
 * @Version: v1.0
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CanalMessage {
    @JsonProperty("type")
    private String type;
 
    @JsonProperty("table")
    private String table;
 
    @JsonProperty("data")
    private List<Object> data;
 
    @JsonProperty("database")
    private String database;
 
    @JsonProperty("es")
    private Long es;
 
    @JsonProperty("id")
    private Integer id;
 
    @JsonProperty("isDdl")
    private Boolean isDdl;
 
    @JsonProperty("old")
    private List<Object> old;
 
    @JsonProperty("pkNames")
    private List<String> pkNames;
 
    @JsonProperty("sql")
    private String sql;
 
    @JsonProperty("ts")
    private Long ts;
 
}