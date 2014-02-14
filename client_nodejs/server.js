var net = require('net'),
    http = require('http'),
    thrift = require('thrift'),
    weather = require('./lib/weather/WeatherService'),
    weatherTypes = require('./lib/weather/forecast_types');

//var thriftConn = connectUsingThrift();
//getForecast(thriftConn.client, thriftConn.connection, function(err, resp) {
//    console.log('Forecast came back from Thrift connection!', err, resp);
//});


//var tcpConn = connectUsingTCP();
//getForecast(tcpConn.client, tcpConn.connection, function(err, resp) {
//    console.log('Forecast came back from TCP connection!', err, resp);
//});

var httpConn = connectUsingHTTP();
getForecast(httpConn.client, httpConn.connection, function (err, resp) {
    console.log('Forecast came back from HTTP connection!', err, resp);
});


/**
 * Normal Thrift connection using TCP
 */
function connectUsingThrift() {
    var connection = thrift.createConnection('localhost', 9090),
        client = thrift.createClient(weather, connection);

    connection.on('error', function(err) {
        console.error('Error connecting via Thrift.', err);
    });

    return {
        client:client,
        connection:connection
    };
}

/**
 * Connect using a new TCP socket
 */
function connectUsingTCP() {
    var socket = net.createConnection({
        port: 9090,
        host: 'localhost'
    });

    var connection = new thrift.Connection(socket);

    connection.on('error', function(err) {
        console.error('Error connecting via TCP.', err);
    });

    var client = thrift.createClient(weather, connection);

    return {
        client:client,
        connection:connection
    };
}

/**
 * Connect using a new HTTP connection
 */
function connectUsingHTTP() {
    var httpClient = http.request({
        host: 'localhost',
        port: 8080,
//        method: 'POST',
//        agent: false
    });

    httpClient.on('socket', function (socket) {
        console.log('Socket Assigned.');
        socket.on('connect', function(arg1, arg2, arg3) {
            httpClient.emit('connect', arg1, arg2, arg3)
        });
    });

    var connection = new thrift.Connection(httpClient);

//    httpClient.__PHIL = "THIS IS OUR STREAM!!!";

    connection.on('error', function(err) {
        console.error('Error connecting to the servlet.', err);
        console.error(err);
    });

    var client = thrift.createClient(weather, connection);

//    httpClient.on('data', function (data) {
//        console.log('Something came back...', data);
//    });
//
//    httpClient.on('socket', function (d) {
//        console.log('Socket Assigned.', d)
//    });
//    httpClient.on('connect', function (d) {
//        console.log('Connect event fired.', d)
//    });
//    httpClient.on('response', function (d) {
//        console.log('Response event fired.', d)
//    });


//    httpClient.end();

    return {
        client:client,
        connection:connection
    };
}

/**
 * Retrieve the weather forecast from the remote service
 * @param client
 * @param connection
 * @param callback
 */
function getForecast(client, connection, callback) {
    client.getForecast('today', function(err, response) {
        if (err) throw err;

        console.log('Weather forecast for today: low of %d, high of %d. %s', response.low, response.high, response.comment);
        connection.end();

        if (callback) {
            callback(err, response);
        }
    });
}
