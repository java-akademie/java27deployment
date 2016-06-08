package ch.jmildner.log4j2;

import org.apache.log4j.Logger;

public class TestLog4J2
{

	public static void main(String[] args)
	{
		new TestLog4J2().go();

	}


	Logger log = Logger.getLogger(TestLog4J2.class);


	private void go()
	{
		log.fatal("2 fatal");
		log.error("2 error");
		log.warn("2 warn");
		log.info("2 info");
		log.debug("2 debug");
		log.trace("2 trace");
	}

}
