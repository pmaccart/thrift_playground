namespace java com.pmaccart.thrift.model

struct Forecast {
	1: string date,
	2: i32 low,
	3: i32 high,
	4: optional string comment
}

exception InvalidRequestException {
	1: i32 code,
	2: string reason
}

service WeatherService {
	void ping(),

	Forecast getForecast(1:string date) throws (1:InvalidRequestException ire)
}