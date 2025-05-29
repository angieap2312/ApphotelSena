package com.sena.app_hotel.Exeptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse {

    private Integer code;
    private String msg;
    private Object obj;

    public ObjectResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
