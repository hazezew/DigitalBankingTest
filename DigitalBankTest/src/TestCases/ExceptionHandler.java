package TestCases;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ExceptionHandler implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String params=testResultArguments(result);
		Reporter.log("Passed : "+result.getName()+params, true);
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String params=testResultArguments(result);
		Reporter.log("Failed : "+result.getName()+params, true);		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String params=testResultArguments(result);
		Reporter.log("Skipped : "+result.getName()+params, true);		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	public String testResultArguments(ITestResult result) {
		StringBuilder arguments = new StringBuilder();
		Object[] inputArgs = result.getParameters();
		
		arguments.append("(");
		if(inputArgs!=null && inputArgs.length>0) {
			for(Object param:inputArgs) {
				if(param==null)
					arguments.append("null");
				else
					arguments.append(param.toString());
				arguments.append(",");
			}
			arguments.delete(arguments.length()-1, arguments.length()); //Delete the last comma 
		}
		arguments.append(")");
		return arguments.toString();
	}
}
