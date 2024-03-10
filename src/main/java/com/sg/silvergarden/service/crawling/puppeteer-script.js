const puppeteer = require('puppeteer');
const axios = require('axios');
const moment = require('moment'); // moment 라이브러리 추가

async function performCrawling1() {
    const browser = await puppeteer.launch({ devtools: false });
    const page = await browser.newPage();
    await page.goto('http://www.kaswcs.or.kr/bj_board/bjbrd_list.htm?board_id=0307');
    await page.waitForSelector('tbody');

    const links = await page.$$eval('td.title a', anchors => anchors.map(anchor => anchor.href));
    const hrefArr = [];
    for (let href of links) {
        await page.goto(href);
        await page.waitForSelector('div.board_view');

        const siteName = '한국노인종합복지관협회';
        const title = await page.$eval('#contents > div > div.board_view1.mt45 > div.board_view_top > h2', el => el.innerText.replace(/\n/g, ' ').trim());
        const date = await page.$eval('.board_info', (boardInfo) => {
            const text = boardInfo.innerText;
            const dateRegex = /\d{4}-\d{2}-\d{2}/; // YYYY-MM-DD 형식의 날짜를 찾는 정규 표현식
            const match = text.match(dateRegex);
            return match ? match[0] : ''; // 매칭되는 패턴이 있으면 첫 번째 매치를 반환, 없으면 빈 문자열 반환
        });
        const isContentExist = await page.$('#contents > div > div.board_view1.mt45 > div.board_view');
        let content = '';
        if (isContentExist) {
            content = await page.$eval('#contents > div > div.board_view1.mt45 > div.board_view', el => el.innerText.replace(/\s+/g, ' ').trim());
        } else {
            content = '링크 참고';
        }
        hrefArr.push({ siteName, href, title, date, content });
    };
    await browser.close();
    return hrefArr;
}
async function performCrawling2() {
    const browser = await puppeteer.launch({ devtools: false });
    const page = await browser.newPage();
    await page.goto('http://www.sacold.or.kr/bbs/board.php?bo_table=data2');
    await page.waitForSelector('tbody');
    const links = await page.$$eval('.bo_tit a', anchors => anchors.map(anchor => anchor.href));
    const hrefArr2 = [];

    for (let href of links) {
        await page.goto(href);
        await page.waitForSelector('#bo_v_title > span');
        const siteName = '서울시재가노인복지협회';
        const title = await page.$eval('#bo_v_title > span', el => el.innerText.replace(/\n/g, ' ').trim());
        const rawDate = await page.$eval('.fa-clock-o', (clockIcon) => { //24-03-06 14:14 YY-MM-DD HH:mm 형식
            const text = clockIcon.parentNode.innerText;
            const dateRegex = /\d{2}-\d{2}-\d{2} \d{2}:\d{2}/;
            const match = text.match(dateRegex);
            return match ? match[0] : '';
        });
        const date = moment(rawDate, 'YY-MM-DD HH:mm').format('YYYY-MM-DD');
        const isContentExist = await page.$('#bo_v_con');
        let content = '';
        if (isContentExist) {
            const isImageExist = await page.$('#bo_v_con img');
            if (isImageExist) {
                content = '링크 참고';
            } else {
                content = await page.$eval('#bo_v_con', el => el.innerText.replace(/\s+/g, ' ').trim());
            }
        } else {
            content = '링크 참고';
        }
        hrefArr2.push({ siteName, href, title, date, content });
    };
    await browser.close();
    return hrefArr2;
}
async function performCrawling3() {
    const browser = await puppeteer.launch({ devtools: false });
    const page = await browser.newPage();
    await page.goto('http://www.kacold.or.kr/weel_bbs/board.php?bo_table=welfare');
    await page.waitForSelector('tbody');
    const links = await page.$$eval('.td_subject a', anchors => anchors.map(anchor => anchor.href));
    const hrefArr3 = [];

    for (let href of links) {
        await page.goto(href);
        await page.waitForSelector('#bo_v');

        const siteName = '(사)한국재가노인복지협회';
        const title = await page.$eval('#bo_v_title', el => el.innerText.replace(/\n/g, ' ').trim());
        const rawDate = await page.$eval('#bo_v_info', (boardInfo) => {
            const text = boardInfo.parentNode.innerText;
            const dateRegex = /\d{2}-\d{2}-\d{2} \d{2}:\d{2}/;
            const match = text.match(dateRegex);
            return match ? match[0] : '';
        });
        const date = moment(rawDate, 'YY-MM-DD HH:mm').format('YYYY-MM-DD');
        const isContentExist = await page.$('#bo_v_atc');
        let content = '';
        if (isContentExist) {
            content = await page.$eval('#bo_v_atc', el => el.innerText.replace(/\s+/g, ' ').trim());
        } else {
            content = '링크 참고';
        }

        hrefArr3.push({ siteName, href, title, date, content });
    };
    await browser.close();
    return hrefArr3;
}
async function runCrawlings() {
    try {
        const data1 = await performCrawling1();
        const data2 = await performCrawling2();
        const data3 = await performCrawling3();
        const combinedData = [...data1, ...data2, ...data3]; // 두 크롤링 결과를 합침
        await sendCrawledDataToServer(combinedData);
    } catch (error) {
        console.error('Error during crawlings:', error);
    }
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

runCrawlings();
