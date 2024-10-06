const express = require('express');
const config = require('./config');
const app = express();

if (!config.headless) {
    app.set('view engine', 'ejs');
}

function sendEvent(type, treatmentId) {
    const url = `${config.reporting}/event`;
    const data = {
        type: type,
        assetId: config.assetId,
        treatmentId: treatmentId
    };
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };
    console.log(options);
    fetch(url, options);
}

app.get('/convert', (req, res) => {
    const treatmentId = parseInt(req.query.treatmentId);
    sendEvent('CLICK', treatmentId);
    res.redirect('/');
});

// app.get('/noconvert', (req, res) => {
//     const treatmentId = parseInt(req.query.treatmentId);
//     sendEvent('PAGE_VIEW', treatmentId);
//     res.redirect('/');
// });

app.get('/', async (req, res) => {
    const treatment = await getTreatmentFromDecisioningApi();
    const treatmentObject = { treatmentName: treatment.name, treatmentId: treatment.id };
    sendEvent('PAGE_VIEW', treatmentObject.treatmentId);

    if (config.headless) {
        treatmentObject.convert_url = `http://localhost:${config.port}/convert?treatmentId=` + treatment.id;
        //treatmentObject.no_convert_url = `http://localhost:${config.port}/noconvert?treatmentId=` + treatment.id;
        res.send(treatmentObject);
    } else {
        res.render('index', treatmentObject);
    }
});

app.listen(config.port, () => {
  console.log(`Example app listening at http://localhost:${config.port}`);
});

async function getTreatmentFromDecisioningApi() {
    // implement http call to decisioning
    const url = `${config.decisioning}/decisioning/${config.decisioningStrategy}/${config.assetId}`;
    console.log(url);
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });

    const obj = await response.json();
    return obj;
}