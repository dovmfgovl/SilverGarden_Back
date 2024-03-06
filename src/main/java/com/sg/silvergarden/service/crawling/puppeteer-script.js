const puppeteer = require('puppeteer');
const cheerio = require('cheerio');
const axios = require('axios');
const moment = require('moment'); // moment 라이브러리 추가

function normalizeDate(originalDate) {
    // moment 라이브러리를 사용하여 날짜를 표준 형식으로 변환
    const parsedDate = moment(originalDate, ['YYYY-MM-DD', 'YYYY.MM.DD'], true);
    return parsedDate.isValid() ? parsedDate.format('YYYY-MM-DD') : originalDate;
}
async function performCrawling() {
    const browser = await puppeteer.launch({
        devtools: false
    });
    const hrefArr = [];
    const page = await browser.newPage();
    await page.goto('http://www.kaswcs.or.kr/bj_board/bjbrd_list.htm?board_id=0307');
    const html = await page.content();
    await page.waitForSelector('tbody');
    const $ = cheerio.load(html);
    $('td.title').each((index, element) => {
        const siteName = '한국노인종합복지관협회';
        const baseUrl = "http://www.kaswcs.or.kr/bj_board/";
        const url = $(element).find('a').attr('href');
        const href = baseUrl+url
        const title = $(element).find('a').text();
        const date = normalizeDate($(element).next('td').next('td').text());
        hrefArr.push({
            siteName,
            href,
            title,
            date
        });
    });
    const jsonData = JSON.stringify(hrefArr);
    await sendCrawledDataToServer(jsonData);
    await browser.close();
}
async function performCrawling2() {
    const browser = await puppeteer.launch({
        devtools: false
    });
    const hrefArr2 = [];
    const page = await browser.newPage();
    await page.goto('http://www.sacold.or.kr/bbs/board.php?bo_table=data2');
    await page.waitForSelector('tbody');
    const html = await page.content();
    const $ = cheerio.load(html);
    $('tbody tr').each((index, element) => {
        const siteName = '서울시재가노인복지협회';
        const href = $(element).find('a').attr('href');
        const title = $(element).find('a').text().trim();
        const date = normalizeDate($(element).find('td.td_datetime').text().trim());
        hrefArr2.push({
            siteName,
            href,
            title,
            date
        });
    });
    const jsonData = JSON.stringify(hrefArr2);
    await sendCrawledDataToServer(jsonData);
    await browser.close();
}
async function sendCrawledDataToServer(data) {
    console.log("/////////////sendCrawledDataToServer")
    try {
        const response = await axios.post('http://localhost:8000/crawling/dataInsert', data, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
        console.log('Data sent to server:', response.data);
    } catch (error) {
        console.error('Error sending data to server:', error);
    }
}
performCrawling();
performCrawling2();

