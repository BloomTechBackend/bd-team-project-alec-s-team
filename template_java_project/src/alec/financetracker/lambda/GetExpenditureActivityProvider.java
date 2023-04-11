package alec.financetracker.lambda;

import alec.financetracker.activity.GetExpenditureActivity;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.models.results.requests.GetExpenditureRequest;
import alec.financetracker.models.results.requests.GetExpenditureResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class GetExpenditureActivityProvider implements RequestHandler<GetExpenditureRequest, GetExpenditureResult>{
    private static Logger log = null;
    private ExpendituresDAO expendituresDAO;

    public GetExpenditureActivityProvider(){
        log = Logger.getLogger(GetExpenditureActivityProvider.class.getName());
    }

    @Override
    public GetExpenditureResult handleRequest(final GetExpenditureRequest getExpenditureRequest, Context context){
        log.info("Recived GetExpenditureRequest{ " + getExpenditureRequest.toString() + "}");
        return getGetExpenditureActivityObject().handleRequest(getExpenditureRequest, context);
    }
    private GetExpenditureActivity getGetExpenditureActivityObject(){
        GetExpenditureActivity getExpenditureActivity = new GetExpenditureActivity(expendituresDAO);
        return getExpenditureActivity;
    }


}