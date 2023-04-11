package alec.financetracker.lambda;

import alec.financetracker.activity.CreateExpenditureActivity;
import alec.financetracker.dynamodb.ExpendituresDAO;
import alec.financetracker.models.results.requests.CreateExpenditureRequest;
import alec.financetracker.models.results.requests.CreateExpenditureResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class CreateExpenditureActivityProvider implements RequestHandler<CreateExpenditureRequest, CreateExpenditureResult>{
    private static Logger log = null;
    private ExpendituresDAO expendituresDAO;

    public CreateExpenditureActivityProvider(){
        log = Logger.getLogger(CreateExpenditureActivityProvider.class.getName());
    }

    @Override
    public CreateExpenditureResult handleRequest(final CreateExpenditureRequest createExpenditureRequest, Context context){
        log.info("Recived CreateExpenditureRequest{ " + createExpenditureRequest.toString() + "}");
        return getCreateExpenditureActivityObject().handleRequest(createExpenditureRequest, context);
    }
    private CreateExpenditureActivity getCreateExpenditureActivityObject(){
        CreateExpenditureActivity createExpenditureActivity = new CreateExpenditureActivity(expendituresDAO);
        return createExpenditureActivity;
    }


}
