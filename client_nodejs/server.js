var thrift = require('thrift'),
    weather = require('./lib/weather/WeatherService'),
    weatherTypes = require('./lib/weather/forecast_types');

var connection = thrift.createConnection('localhost', 9090),
    client = thrift.createClient(weather, connection);

connection.on('error', console.error);

client.getForecast('today', function(err, response) {
    if (err) throw err;

    console.log('Weather forecast for today: low of %d, high of %d. %s', response.low, response.high, response.comment);
    connection.end();
});

