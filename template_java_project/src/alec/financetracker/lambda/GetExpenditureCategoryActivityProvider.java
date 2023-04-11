package alec.financetracker.lambda;

import alec.financetracker.activity.GetExpenditureCategoryActivity;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.models.results.requests.GetExpenditureCategoryRequest;
import alec.financetracker.models.results.requests.GetExpenditureCategoryResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class GetExpenditureCategoryActivityProvider implements RequestHandler<GetExpenditureCategoryRequest, GetExpenditureCategoryResult>{
    private static Logger log = null;
    private ExpendituresDAO expendituresDAO;

    public GetExpenditureCategoryActivityProvider(){
        log = Logger.getLogger(GetExpenditureCategoryActivityProvider.class.getName());
    }

    @Override
    public GetExpenditureCategoryResult handleRequest(final GetExpenditureCategoryRequest getExpenditureCategoryRequest, Context context){
        log.info("Recived GetExpenditureRequest{ " + getExpenditureCategoryRequest.toString() + "}");
        return getGetExpenditureCategoryActivityObject().handleRequest(getExpenditureCategoryRequest, context);
    }
    private GetExpenditureCategoryActivity getGetExpenditureCategoryActivityObject(){
        GetExpenditureCategoryActivity getExpenditureCategoryActivity = new GetExpenditureCategoryActivity(expendituresDAO);
        return getExpenditureCategoryActivity;
    }


}