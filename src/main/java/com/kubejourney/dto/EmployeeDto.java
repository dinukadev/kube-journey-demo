package com.kubejourney.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kubejourney.dto.serializers.JsonJodaDateTimeDeserializer;
import com.kubejourney.dto.serializers.JsonJodaDateTimeSerializer;
import org.joda.time.DateTime;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class EmployeeDto implements Serializable {

    private final String id;

    private final String name;

    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonDeserialize(using = JsonJodaDateTimeDeserializer.class)
    private final DateTime dob;

    private final String department;

    public EmployeeDto(String id, String name, DateTime dob, String department) {
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
