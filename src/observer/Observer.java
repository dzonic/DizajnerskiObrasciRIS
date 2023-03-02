package observer;


public interface Observer {
	void update(int currentState,int numberOfSelectedShapes, int numOfShapes, int numOfUnexecutedCmd,int numOfExecutedCmd, int numOfLogs, String typeOfFile);
}
