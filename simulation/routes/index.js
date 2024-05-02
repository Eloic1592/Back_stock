var express = require('express');
var router = express.Router();
var database = require('./sensor.json')
const axios = require('axios')

/* GET home page. */
router.get('/', function(req, res, next) {
  // console.log(database)
  startSimulation().then(r => {

  })
  res.render('index', res.json("OK"));
});

const startSimulation = async () => {
  database.map(data => setInterval(() => {
    call(data)
  }, 1000))
}

const call = (data) => {
  let url = 'http://192.168.8.100:8087/clean-apj/electric/state/load'
  url += "?macAddress="+data['mac']
  url += "&latency=" + data['latency']
  // console.log(url)
  axios.post(url, {}).then(res => {}).catch(err =>() => {
    console.log("error")
  })
  return 0
}

module.exports = router;
