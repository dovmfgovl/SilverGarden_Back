package com.sg.silvergarden.controller.attendance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Slf4j
@Component
public class HolidayCheckTest {
    public boolean isHoliday() {
        // 현재 날짜를 가상의 공휴일로 설정
        int fakeHolidayYear = 2024;
        int fakeHolidayMonth = 03;
        int fakeHolidayDay = 03;

        // 현재 날짜와 가상의 공휴일을 비교하여 공휴일 여부 확인
        int todayYear = getYear();
        int todayMonth = getMonth();
        int todayDate = getDate();

        if (todayYear == fakeHolidayYear && todayMonth == fakeHolidayMonth && todayDate == fakeHolidayDay) {
            return true; // 가상의 공휴일이면 true 반환
        }

        return false; // 가상의 공휴일이 아니면 false 반환
    }

    // 현재 날짜의 연도 반환
    private int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    // 현재 날짜의 월 반환
    private int getMonth() {
        String month = String.format("%02d", Calendar.getInstance().get(Calendar.MONTH) + 1); // Calendar.MONTH는 0부터 시작하므로 +1 , api 사용시 3이 아닌 03으로 검색해야하므로 format 사용
        log.info("month: " + month);
        return Integer.parseInt(month); // 문자열을 int로 변환
    }

    // 현재 날짜의 일 반환 (한 자리 숫자일 경우에도 두 자리로 반환)
    private int getDate() {
        String day = String.format("%02d", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        log.info("day: " + day);
        return Integer.parseInt(day);
    }

}
