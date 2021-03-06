package org.iugonet.www;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.net.URI;
import java.beans.*;
import java.io.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WindMfih0Test {
	WindMfih0 windMfih0;
	String url;
	String filepath;
	
	@Before
	public void setUp() throws Exception {
		windMfih0 = new WindMfih0();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		url = "http://spdf.gsfc.nasa.gov";
		filepath = "/pub/data/wind/mfi/mfi_h0/1994/wi_h0_mfi_19941113_v05.cdf";
		windMfih0.file_http_copy(url+filepath);
		windMfih0.read(filepath);
		
		TimeSeries[] timeSeries = windMfih0.getTimeSeries();

		for (int i = 0; i < timeSeries.length; i++) {
			System.out.println(timeSeries[i].getKey());
		}
		
		for (int i = 0; i < timeSeries[2].getItemCount(); i++) {
			System.out.print(timeSeries[2].getTimePeriod(i).getStart() + " ");
			System.out.println(timeSeries[2].getDataItem(i).getValue());
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
