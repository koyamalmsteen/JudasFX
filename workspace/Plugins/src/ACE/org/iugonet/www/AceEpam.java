package org.iugonet.www;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import javafx.scene.chart.XYChart.Series;
import lombok.Data;

@Data
public class AceEpam extends Tplot {

	private BufferedReader bufferedReader;

	public AceEpam() {
		super();
		//timeSeries[0].setKey("ACE Electron, Proton, and Alpha Monitor");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	void read(URL url) {

		String line;

		try {
			FileReader fileReader = new FileReader("/tmp" + url.getPath());
			bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				if (!line.substring(0, 1).equals(":")
						&& !line.substring(0, 1).equals("#")) { // skip header
					int yyyy = Integer.parseInt(line.substring(0, 4));
					int month = Integer.parseInt(line.substring(5, 7));
					int day = Integer.parseInt(line.substring(8, 10));
					int hour = Integer.parseInt(line.substring(12, 14));
					int min = Integer.parseInt(line.substring(14, 16));

					// Electron Differential Flux [particles/cm2-s-ster-MeV]
					int stat_e = Integer.parseInt(line.substring(34, 35));
					double electron1 = Double.parseDouble(line
							.substring(35, 45)); // 38-53
					double electron2 = Double.parseDouble(line
							.substring(45, 55)); // 175-315

					// Proton Differential Flux [particles/cm2-s-ster-MeV]
					int stat_p = Integer.parseInt(line.substring(57, 58));
					double proton1 = Double.parseDouble(line.substring(58, 68)); // 47-68
					double proton2 = Double.parseDouble(line.substring(68, 78)); // 115-195
					double proton3 = Double.parseDouble(line.substring(78, 88)); // 310-580
					double proton4 = Double.parseDouble(line.substring(88, 98)); // 795-1193
					double proton5 = Double
							.parseDouble(line.substring(98, 108)); // 1060-1900

					double anis = Double.parseDouble(line.substring(108, 115)); // 1060-1900

					if (stat_e == 0) {
						Minute minute = new Minute(min, hour, day, month, yyyy);
						// this.add(minute, electron1);
					}

					if (stat_p == 0) {
						Minute minute = new Minute(min, hour, day, month, yyyy);
						this.add(minute, proton5, 0);
					}

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Series load(URL url) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void plot() {
		
	}

}
