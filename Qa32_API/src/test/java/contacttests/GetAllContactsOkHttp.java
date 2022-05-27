package contacttests;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.GetAllContactsDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllContactsOkHttp {

    Gson gson=new Gson();// biblioteka otvechayushaya za prevrasheniya : class to json ====fro json
    OkHttpClient client=new OkHttpClient(); //otvechaet za knopku "send", vipolniaet zapros
    String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";

    @Test
    public void getAllContactsSuccess() throws IOException {
        Request request=new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .addHeader("Authorization", token)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertEquals(response.code(),200);

        GetAllContactsDto contactsDto = gson.fromJson(response.body().string(),GetAllContactsDto.class);

        List<ContactDto> list = contactsDto.getContacts();
        for(ContactDto contact:list){
            System.out.println(contact.getEmail());
            System.out.println(contact.getId());
            System.out.println("********");
        }


    }
}
