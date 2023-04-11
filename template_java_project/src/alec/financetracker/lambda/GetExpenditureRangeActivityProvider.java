package alec.financetracker.lambda;

import alec.financetracker.activity.GetExpenditureRangeActivity;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.models.results.requests.GetExpenditureRangeRequest;
import alec.financetracker.models.results.requests.GetExpenditureRangeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class GetExpenditureRangeActivityProvider implements RequestHandler<GetExpenditureRangeRequest, GetExpenditureRangeResult>{
    private static Logger log = null;
    private ExpendituresDAO expendituresDAO;

    public GetExpenditureRangeActivityProvider(){
        log = Logger.getLogger(GetExpenditureCategoryActivityProvider.class.getName());
    }

    @Override
    public GetExpenditureRangeResult handleRequest(final GetExpenditureRangeRequest getExpenditureRangeRequest, Context context){
        log.info("Recived GetExpenditureRequest{ " + getExpenditureRangeRequest.toString() + "}");
        return getGetExpenditureRangeActivityObject().handleRequest(getExpenditureRangeRequest, context);
    }
    private GetExpenditureRangeActivity getGetExpenditureRangeActivityObject(){
        GetExpenditureRangeActivity getExpenditureRangeActivity = new GetExpenditureRangeActivity(expendituresDAO);
        return getExpenditureRangeActivity;
    }


}