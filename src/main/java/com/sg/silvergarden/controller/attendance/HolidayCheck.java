package com.sg.silvergarden.controller.attendance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Calendar;

@Slf4j
@Component
public class HolidayCheck {

    @Value("${holiday.api.url}") // application.yml
    private String holidayApiUrl;

    @Value("${holiday.api.key}")
    private String holidayApiKey;

    public boolean isHoliday() {
        // API URL 설정
        String apiUrl = holidayApiUrl + "/getRestDeInfo?solYear=" + getYear() + "&solMonth=" + getMonth() + "&ServiceKey=" + holidayApiKey;

        // RestTemplate을 통한 API 요청
        RestTemplate restTemplate = new RestTemplate();
        String xmlResponse = restTemplate.getForObject(apiUrl, String.class);

        try {
            // XML 파싱
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlResponse)));

            // XML에서 공휴일 정보 추출
            NodeList items = document.getElementsByTagName("item");
            int todayYear = getYear();
            int todayMonth = getMonth();
            int todayDate = getDate();

            for (int i = 0; i < items.getLength(); i++) {
                Element item = (Element) items.item(i);
                String locDate = item.getElementsByTagName("locdate").item(0).getTextContent();
                int holidayYear = Integer.parseInt(locDate.substring(0, 4));
                int holidayMonth = Integer.parseInt(locDate.substring(4, 6));
                int holidayDay = Integer.parseInt(locDate.substring(6, 8));

                if (todayYear == holidayYear && todayMonth == holidayMonth && todayDate == holidayDay) {
                    return true; // 오늘이 공휴일이라면 true 반환
                }
            }
        } catch (Exception e) {
            log.error("XML 파싱 응답 에러: {}", e.getMessage());
        }

        return false; // 오늘이 공휴일이 아니라면 false 반환
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