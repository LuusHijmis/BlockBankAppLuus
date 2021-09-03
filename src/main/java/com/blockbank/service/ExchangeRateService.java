package com.blockbank.service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class ExchangeRateService {

    private static String apiKey = "37df24be-be71-4ebf-addb-b5a5c0e908ce";
    private final static String URI ="https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
    private final static String CURRENCY = "USD";

    //TODO currency wordt via pulldownmenu meegegeven vanaf frontend
    public static double getExchangeRate(String assetID, String currency) {
        //currency = CURRENCY;
        double result = 0;
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("convert", currency));
        paratmers.add(new BasicNameValuePair("id", assetID));
        paratmers.add(new BasicNameValuePair("aux", "platform"));

        try {
            result = makeAPICall(URI, paratmers);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public static double makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException{
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
          EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        List<String> myList = Arrays.asList(response_content.split(","));
        List<String> pricelist = Arrays.asList(myList.get(12).split(":"));
        double price = Double.parseDouble(pricelist.get(3));
        return price;
    }
}

