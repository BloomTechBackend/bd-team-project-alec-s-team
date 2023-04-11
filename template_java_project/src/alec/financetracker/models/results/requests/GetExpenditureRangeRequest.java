package alec.financetracker.models.results.requests;

import alec.financetracker.activity.CreateExpenditureActivity;

public class GetExpenditureRangeRequest {

    private String startDate;
    private String endDate;


    public GetExpenditureRangeRequest(){
    }

    public GetExpenditureRangeRequest(String startDate, String endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public GetExpenditureRangeRequest(Builder builder){
        this.startDate= builder.startDate;
        this.startDate = builder.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStartDate(){
        return startDate;
    }
    public String getEndDate(){
        return endDate;
    }
    public static final class Builder{
        String startDate;
        String endDate;
        private Builder(){

        }
        public Builder wtihStartDate(String startDate){
            this.startDate = startDate;
            return this;
        }
        public Builder withEndDate(String endDate){
            this.endDate = endDate;
            return this;
        }
        public GetExpenditureRangeRequest build(){
            return new GetExpenditureRangeRequest(this);
        }
    }
}