public class Response {
    private boolean success;
    private String data;

    public Response(boolean suc, String dat){
        success = suc;
        data = dat;
    }
    public boolean getSuccess(){return success;};

    public String getData(){return data;};

    public void setSuccess(boolean status){this.success = status;};

    public void setData(String data){this.data = data;};

}
