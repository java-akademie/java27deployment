package ch.jmildner.log4j;

import org.apache.log4j.Logger;

public class TestLog4J1
{
	private static final Logger LOG = Logger
			.getLogger(TestLog4J1.class);



	public static void main(String[] args) throws InterruptedException
	{
		LOG.fatal("1 fatal yyy");
		LOG.error("1 error");
		LOG.warn("1 warn");

		Thread.sleep(1000);

		if (LOG.isInfoEnabled())
			LOG.info("1 info");

		if (LOG.isDebugEnabled())
			LOG.debug("1 debug");

		if (LOG.isTraceEnabled())
			LOG.trace("1 trace");
	}
}
