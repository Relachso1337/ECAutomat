const puppeteer = require('puppeteer');
const fs = require('fs/promises');

async function scrapeProduct(url) {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto(url);

    var obj = {menu: []};

    const wochentag = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"];

    const date = new Date();

    //MENÜ 1

    const [el] = await page.$x('//*[@id="tab-' + wochentag[date.getDay()-1] + '"]/div/div[1]/small');

    if(el != null) {
        const menu = await el.getProperty('textContent');
        const menuTxt = await menu.jsonValue();
        obj.menu.push({id: 1, essen: menuTxt});
    } else {
        obj.menu.push({id: 1, essen: "-"});
    }

    //MENÜ 2
    const [el2] = await page.$x('//*[@id="tab-' + wochentag[date.getDay()-1] + '"]/div/div[2]/small');

    if(el2 != undefined) {
        const menu2 = await el2.getProperty('textContent');
        const menuTxt2 = await menu2.jsonValue();

        obj.menu.push({id: 2, essen: menuTxt2});
    } else {
        obj.menu.push({id: 2, essen: "-"});
    }
    
    //MENÜ 3
    const [el3] = await page.$x('//*[@id="tab-' + wochentag[date.getDay()-1] + '"]/div/div[3]/small');

    if(el3 != null) {
        const menu3 = await el3.getProperty('textContent');
        const menuTxt3 = await menu3.jsonValue();
        obj.menu.push({id: 3, essen: menuTxt3});
    } else {
        obj.menu.push({id: 3, essen: "-"});
    }

    var json = JSON.stringify(obj);

    fs.writeFile('../menu.json', json, 'utf8');

    browser.close();
}

scrapeProduct('https://www.swfr.de/essen/mensen-cafes-speiseplaene/mensa-offenburg');