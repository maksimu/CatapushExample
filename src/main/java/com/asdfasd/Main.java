package com.asdfasd;

import lombok.AllArgsConstructor;
import lombok.Data;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.List;

public class Main {



    public static void main(String...args) throws IOException {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.catapush.com/1/")
                .build();

        LetsCatchThatCatService letsCatchThatCatService = retrofit.create(LetsCatchThatCatService.class);

        MobApp ma = Construct your mobile app;

        Call<CatapushMessageResponse> catapushMessageResponseCall = letsCatchThatCatService.sendPush(ma);

        Response<CatapushMessageResponse> responseResponse = catapushMessageResponseCall.execute();

        int respCode = responseResponse.code(); // per docs: 201 - Message created correctly

    }


    public interface LetsCatchThatCatService{
        @POST("messages")
        Call<CatapushMessageResponse> sendPush(
                @Header("accept") String accept, // application/json
                @Header("content-type") String contentType, // application/json
                @Header("authorization") String authorization, // "Bearer ACCESS_TOKEN"
                @Body MobApp mobApp);
    }

    // Below DTOs based on http://www.catapush.com/docs-api?java#2.1-post---send-a-new-message

    @Data
    @AllArgsConstructor
    static class MobApp{
        private int mobileAppId;
        private String text;
        private String notificationText;
        private String messageRef;
        private NotifyCallback notifyCallback;
        private List<Recipient> recipients;
        private DeviceData devideData;
    }

    @Data
    @AllArgsConstructor
    static class NotifyCallback{
        private String method;
        private String url;
    }

    @Data
    @AllArgsConstructor
    static class Recipient{
        private String identifier;
        private String recipientRef;
        private Override override;
    }

    @Data
    @AllArgsConstructor
    static class DeviceData{
        private Dd ios;
        private Dd android;
    }

    @Data
    @AllArgsConstructor
    static class Dd {

        /**
         * Title of the message
         * Android only
         *
         * string
         * Max. length: 400
         * Safe Characters
         */
        private String title;
        private String category;
        private String soundPath;
        private int badge;
        private boolean sound;
        private CustomData customData;
    }

    @Data
    @AllArgsConstructor
    static class CustomData{
        private String exampleKey;
    }

    @AllArgsConstructor
    static class Override{

        /**
         * Override normal text for the recipient
         */
        String text;

        /**
         * Recipient number (international format without + or 00) used to send the SMS Fallback. By default the recipient identifier is used
         */
        String mobileNumber;
    }




    @Data
    @AllArgsConstructor
    static class CatapushMessageResponse{
        /**
         * Unique identifier of the message
         */
        String messageId;

        /**
         * Creation datetime (rfc-3339)
         */
        String createdAt;

        /**
         * Unique identifier of the mobile app
         */
        int mobileAppId;

        /**
         * Text of the message
         */
        String text;

    }
}
