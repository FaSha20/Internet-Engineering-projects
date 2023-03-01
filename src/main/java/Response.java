import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Response {
    private boolean success;
    private Object data;

    public Response(boolean suc, Object dat){
        success = suc;
        data = dat;
    }
   public boolean getSuccess(){return success;};

    public Object getData(){return data;};

    public void setSuccess(boolean status){this.success = status;};

    public void setData(Object data){this.data = data;};

}
