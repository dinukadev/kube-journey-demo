package com.kubejourney.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kubejourney.dto.serializers.JsonJodaDateTimeDeserializer;
import com.kubejourney.dto.serializers.JsonJodaDateTimeSerializer;
import org.joda.time.DateTime;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class EmployeeDto implements Serializable {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonProperty("dob")
    private final DateTime dob;

    @JsonProperty("department")
    private final String department;

    @JsonCreator
    public EmployeeDto(@JsonProperty("id") String id,
                       @JsonProperty("name") String name,
                       @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class) @JsonProperty("dob") DateTime dob,
                       @JsonProperty("department") String department) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateTime getDob() {
        return dob;
    }

    public String getDepartment() {
        return department;
    }
}
