package contacttests;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkHttp {

    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson=new Gson();// biblioteka otvechayushaya za prevrasheniya : class to json ====fro json
    OkHttpClient client=new OkHttpClient(); //otvechaet za knopku "send", vipolniaet zapros

    @Test
    public void loginSuccess() throws IOException {
        AuthRequestDto requestDto=AuthRequestDto.builder().email("noa@gmail.com").password("Nnoa12345$").build();
        RequestBody requestBody=RequestBody.create(gson.toJson(requestDto),JSON);
        Request request=new Request.Builder().url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute(); //alt+enter --> rezultat bizova vitiagivaem v peremennuyu
        AuthResponseDto responseDto=gson.fromJson(response.body().string(),AuthResponseDto.class);// poluchimclass java
        String token=responseDto.getToken();
        System.out.println("THIS IS OUR TOKEN---->" +token);


        Assert.assertEquals(response.code(),200);
        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void loginWrongEmail()throws  IOException{
        AuthRequestDto requestDto=AuthRequestDto.builder().email("noa_gmail.com").email("Nnoa12345$").build();
        RequestBody requestBody=RequestBody.create(gson.toJson(requestDto),JSON);

        Request request=new Request.Builder().url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute(); //alt+enter --> rezultat bizova vitiagivaem v peremennuyu

        ErrorDto errorDto=gson.fromJson(response.body().string(),ErrorDto.class);

        String message=errorDto.getMessage();
        Assert.assertEquals(message,"Wrong email format! Example: name@mail.com");
        Assert.assertTrue(message.contains("Wrong email format!"));

       // Assert.assertEquals(response.code(),200);
        Assert.assertNotEquals(response.code(),200);
        Assert.assertFalse(response.isSuccessful());
    }
}
