package org.iugonet.www;

import java.net.URI;
import java.beans.*;
import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.jfree.data.time.TimeSeries;

public class MuIsdataDriftNcdfTest {
	MuIsdataDriftNcdf muIsdataDriftNcdf;
	String url;
	String filepath;

	@Before
	public void setUp() throws Exception {
		muIsdataDriftNcdf = new MuIsdataDriftNcdf();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		url = "http://www.rish.kyoto-u.ac.jp";
		filepath = "/radar-group/mu/isdata/data/drift/netcdf/1992/19920722_drift.nc";

		muIsdataDriftNcdf.download(url + filepath);
		muIsdataDriftNcdf.read(filepath);

		TimeSeries[] timeSeries = muIsdataDriftNcdf.getTimeSeries();
		for (int i = 0; i < 9; i++) {
			System.out.println(timeSeries[i].getKey());
		}
		for (int i = 0; i < timeSeries[8].getItemCount(); i++) {
			System.out.print(timeSeries[8].getTimePeriod(i).getStart() + " ");
			System.out.println(timeSeries[8].getDataItem(i).getValue());
		}
	}

	@Test
	public void xmlEncoderTest() throws Exception {
		URI uri = new URI(
				"spase://IUGONET/Granule/ICSWSE/MAGDAS/AAB/fluxgate/PT1S_ICSWSE_storage/AAB_SEC_200811010000_mgd");
		sample.read(uri);
		
		try{
			XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("goods.xml")));
			xmlEncoder.writeObject(goodsArray);
			xmlEncoder.close();
		} catch(FileNotFoundException ex){
			System.err.println(ex);
		}
	}
	
	@Test
	public void xmlDecoderTest() throws Exception {
		URI uri = new URI(
				"spase://IUGONET/Granule/ICSWSE/MAGDAS/AAB/fluxgate/PT1S_ICSWSE_storage/AAB_SEC_200811010000_mgd");
		sample.read(uri);
		
		try{
			XMLDecoder xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("goods.xml")));
			sample = (Sample)xmlDecoder.readObject();
			xmlDecoder.close();
		}catch(FileNotFoundException ex){
			System.err.println(ex);
			return;
		}
	}
}
