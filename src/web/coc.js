// eslint-disable-next-line no-unused-vars
function updateAttr() {
    var chAttr = {
        'STR': document.getElementById('STR').value,
        'CON': document.getElementById('CON').value,
        'SIZ': document.getElementById('SIZ').value,
        'DEX': document.getElementById('DEX').value,
        'APP': document.getElementById('APP').value,
        'INT': document.getElementById('INT').value,
        'POW': document.getElementById('POW').value,
        'EDU': document.getElementById('EDU').value
    };

    var hp = (Number(chAttr.SIZ) + Number(chAttr.CON)) / 10;
    document.getElementById('hp').innerHTML = parseInt(hp).toString();
    var mp = Number(chAttr.POW) / 5;
    document.getElementById('mp').innerHTML = parseInt(mp).toString();
    var san = Number(chAttr.POW);
    document.getElementById('san').innerHTML = parseInt(san).toString();
}

// eslint-disable-next-line no-unused-vars
function remember() {
    var character;
    var chAttr;
    var chInfo;
    var attr;
    chInfo = {
        'plName': document.getElementById('plName').value,
        'pcName': document.getElementById('pcName').value,
        'sex': document.getElementById('sex').value,
        'job': document.getElementById('job').value,
        'age': Number(document.getElementById('age').value),
        'center': document.getElementById('center').value,
        'addr': document.getElementById('addr').value,
        'home': document.getElementById('home').value
    };
    chAttr = {
        'str': Number(document.getElementById('STR').value),
        'con': Number(document.getElementById('CON').value),
        'siz': Number(document.getElementById('SIZ').value),
        'dex': Number(document.getElementById('DEX').value),
        'app': Number(document.getElementById('APP').value),
        'int': Number(document.getElementById('INT').value),
        'pow': Number(document.getElementById('POW').value),
        'edu': Number(document.getElementById('EDU').value)
    };
    attr = {
        'hp': Number(document.getElementById('hp').innerText),
        'hpm': Number(document.getElementById('hp').innerText),
        'mp': Number(document.getElementById('mp').innerText),
        'mpm': Number(document.getElementById('mp').innerText),
        'san': Number(document.getElementById('san').innerText),
        'luck': Number(document.getElementById('luckNum').value)
    };
    character = {
        'chInfo': chInfo,
        'chAttr': chAttr,
        'attr': attr
    };
    console.log('数据已发送！');

    console.log(JSON.stringify(character));

    var httpRequest = new XMLHttpRequest();
    httpRequest.open('POST', '/POST', true);
    httpRequest.setRequestHeader("Content-type", "application/json");
    httpRequest.send(JSON.stringify(character));

    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            document.getElementById("json").innerHTML = httpRequest.responseText;
        }
    }

}
