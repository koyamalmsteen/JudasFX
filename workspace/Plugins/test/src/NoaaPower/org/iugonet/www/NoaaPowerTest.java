package org.iugonet.www;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.net.URI;
import java.beans.*;
import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NoaaPowerTest {

	NoaaPower noaaPower;
	String url;
	String filepath;

	@Before
	public void setUp() throws Exception {
		noaaPower = new NoaaPower();
	}

	@After
	public void tearDown() throws Exception {
		noaaPower = null;
		System.gc();
	}

	@Test
	public void test() {
		url = "http://www.swpc.noaa.gov";
		filepath = "/ftpdir/lists/hpi/power_2013.txt";

		noaaPower.download(url + filepath);
		noaaPower.read(filepath);

		TimeSeries[] timeSeries = noaaPower.getTimeSeries();
		for (int i = 0; i < timeSeries[0].getItemCount(); i++) {
			System.out.print(timeSeries[0].getTimePeriod(i).getStart() + " ");
			System.out.println(timeSeries[0].getDataItem(i).getValue());
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
