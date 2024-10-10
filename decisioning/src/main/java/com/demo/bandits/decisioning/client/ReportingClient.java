package com.demo.bandits.decisioning.client;

import com.demo.bandits.decisioning.client.dto.Treatment;
import com.demo.bandits.decisioning.client.dto.Treatment;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import com.demo.bandits.decisioning.client.dto.Treatment;
import com.demo.bandits.decisioning.client.dto.TreatmentStatisticsDTO;

import java.io.IOException;
import java.util.List;

@Component
public class ReportingClient {
    private static final String REPORTING_URL = "http://localhost:8080";
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public ReportingClient() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Treatment> getTreatmentsForAsset(Long assetId) {
        final String url = REPORTING_URL + "/treatment/asset/" + assetId;
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        final String responseBody = executeCallAndGetResponseString(request);

        try {
            return objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, Treatment.class));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TreatmentStatisticsDTO> getTreatmentsStatisticsForAsset(Long assetId) {
        final String url = REPORTING_URL + String.format("/asset/%d/statistics", assetId);
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .build();

        final String responseBody = executeCallAndGetResponseString(request);

        try {
            return objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructCollectionType(List.class, TreatmentStatisticsDTO.class));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String executeCallAndGetResponseString(Request request) {
        try(final Response response = client.newCall(request).execute()) {
            if(response.body() == null) {
                throw new RuntimeException("Response body is null");
            }
            return response.body().string();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
