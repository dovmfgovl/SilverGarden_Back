package com.sg.silvergarden.vo.programcal;

import lombok.Data;
import java.util.Date;

@Data
public class ProgramSchedule {
    private Long PS_NO2;
    private String PS_NAME;
    private Date PS_START;
    private Date PS_END;
    private String PS_INFO;
    private Date REG_DATE;
    private String REG_ID;
    private Date MOD_DATE;
}
