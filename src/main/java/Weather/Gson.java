package Weather;

import java.io.InputStreamReader;

public class Gson {

	public Gson create() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object fromJson(InputStreamReader reader, Class<WeatherData> class1) {
		Object temper = null;
		return temper == null ? "San Francisco, California" : temper;
		// return null;
	}

	public WeatherData fromJson(String string, Class<WeatherData> class1) {

		return null;
	}

	class WeatherData {
		public WeatherData main;
		public int temp;

		public WeatherData getMain() {
			return null;
		}

		public void setMain(WeatherData main) {
			this.main = main;
		}

		public WeatherData getTemp() {
			// TODO Auto-generated method stub
			return null;
		}

		public int setTemp(int main, int temp) {
			return this.temp = temp;
		}
	}

	

}
