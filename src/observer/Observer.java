package observer;


public interface Observer {
	void update(int currentState,int numberOfSelectedShapes, int numberOfShapes, int numberOfUnexecutedCmd,int numberOfExecutedCmd, int numberOfLogs, String typeOfFile);
}
