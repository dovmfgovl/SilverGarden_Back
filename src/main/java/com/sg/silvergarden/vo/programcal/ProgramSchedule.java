package com.sg.silvergarden.vo.programcal;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ProgramSchedule {
    private String PG_NAME;
    private String PG_CATEGORY;
    private Integer PG_TEACHER;
    private String PG_DAYSOFWEEK;
    private String PG_REPEAT_TYPE;
    private LocalDateTime PG_START;
    private LocalDateTime PG_END;
    private String PG_CONTENT;
}
