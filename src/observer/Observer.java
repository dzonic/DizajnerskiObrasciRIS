package observer;


public interface Observer {
	void update(int currentState,int numOfSelectedShapes, int numOfShapes, int numOfUnexecutedCmd,int numOfExecutedCmd, int numOfLogs, String typeOfFile);
}
