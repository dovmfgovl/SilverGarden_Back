const puppeteer = require('puppeteer');
const cheerio = require('cheerio');
const axios = require('axios');

async function performCrawling() {
    console.log("/////////////performCrawling")
    const browser = await puppeteer.launch({
        devtools: false
    });
    const page = await browser.newPage();
    await page.goto('http://www.kaswcs.or.kr/bj_board/bjbrd_list.htm?board_id=0307');
    const html = await page.content();
    const $ = cheerio.load(html);
    const hrefArr = [];
    $('td.title').each((index, element) => {
        const href = $(element).find('a').attr('href');
        const title = $(element).find('a').text();
        const date = $(element).next('td').next('td').text();
        hrefArr.push({
            href,
            title,
            date
        });
    });
    console.log("hrefArr", hrefArr);
    const jsonData = JSON.stringify(hrefArr, null, 2);
    console.log(jsonData);
    // 바로 서버로 전달이 아닌, 함수로 데이터 전송
    await sendCrawledDataToServer(jsonData);
    await browser.close();
}

async function sendCrawledDataToServer(jsonData) {
    console.log("/////////////sendCrawledDataToServer")

    try {
        // 서버로 데이터 전송
        const response = await axios.post('http://localhost:8000/crawling/dataInsert', jsonData, {
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
