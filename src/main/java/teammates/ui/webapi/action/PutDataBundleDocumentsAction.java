package teammates.ui.webapi.action;

import org.apache.http.HttpStatus;

import teammates.common.datatransfer.DataBundle;
import teammates.common.exception.UnauthorizedAccessException;
import teammates.common.util.Config;
import teammates.common.util.JsonUtils;

/**
 * Puts searchable documents from the data bundle into the DB.
 */
public class PutDataBundleDocumentsAction extends Action {

    @Override
    protected AuthType getMinAuthLevel() {
        return AuthType.ALL_ACCESS;
    }

    @Override
    public void checkSpecificAccessControl() {
        if (!Config.isDevServer()) {
            throw new UnauthorizedAccessException("Admin privilege is required to access this resource.");
        }
    }

    @Override
    public JsonResult execute() {
        DataBundle dataBundle = JsonUtils.fromJson(getRequestBody(), DataBundle.class);
        logic.putDocuments(dataBundle);
        return new JsonResult("Data bundle documents successfully added.", HttpStatus.SC_OK);
    }

}
