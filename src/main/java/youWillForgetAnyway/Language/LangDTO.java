package youWillForgetAnyway.Language;

class LangDTO {
    private Integer id;
    private String code;

    LangDTO(Language lang){
        this.id = lang.getId();
        this.code = lang.getCode();
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
