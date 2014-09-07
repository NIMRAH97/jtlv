package env.wis.jtlv.regression.smv;

import junit.framework.Test;
import junit.framework.TestSuite;
import env.wis.jtlv.regression.smv.legacy.AbpAllAbpTC;
import env.wis.jtlv.regression.smv.legacy.BmcBarrel5TC;
import env.wis.jtlv.regression.smv.legacy.BmcCounterTC;
import env.wis.jtlv.regression.smv.legacy.BmcDmeAllDmeTC;
import env.wis.jtlv.regression.smv.legacy.BmcMcpTC;
import env.wis.jtlv.regression.smv.legacy.BmcMultiplierTC;
import env.wis.jtlv.regression.smv.legacy.BmcTutorialBmcTutorialTC;
import env.wis.jtlv.regression.smv.legacy.BrpBrpTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlAbp4TC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlAbp8TC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlCounterTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlDme2TC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlGuidanceTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlMutex1TC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlMutexTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlPQueueTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlPeriodicTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlProdConsTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlProductionCellTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlRingTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlSemaphoreTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlShortTC;
import env.wis.jtlv.regression.smv.legacy.CtlLtlSyncarb5TC;
import env.wis.jtlv.regression.smv.legacy.NotEQBugTC;

public class SuiteSMVLegacyTests {

	public static Test suite() {
		TestSuite smv_suite = new TestSuite("Tests suite for legacy SMV files.");

		// adding sub testing suites
		// smv_suite.addTest(....suite());

		// adding the actual tests
		smv_suite.addTest(new SMVTestCaseTemplate());

		// ////////// abp folder
		smv_suite.addTest(new AbpAllAbpTC("abp4"));
		smv_suite.addTest(new AbpAllAbpTC("abp8"));
		smv_suite.addTest(new AbpAllAbpTC("abp10"));
		smv_suite.addTest(new AbpAllAbpTC("abp11"));
		smv_suite.addTest(new AbpAllAbpTC("abp16"));
		// ////////// bmc folder
		smv_suite.addTest(new BmcBarrel5TC());
		smv_suite.addTest(new BmcCounterTC());
		smv_suite.addTest(new BmcMcpTC());
		smv_suite.addTest(new BmcMultiplierTC());
		// ////////// bmc/dme folder
		smv_suite.addTest(new BmcDmeAllDmeTC("dme4"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme5"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme6"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme7"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme8"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme9"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme10"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme11"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme12"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme13"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme14"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme15"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme16"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme17"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme18"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme19"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme20"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme30"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme40"));
		smv_suite.addTest(new BmcDmeAllDmeTC("dme50"));
		// ////////// bmc_tutorial folder
		smv_suite.addTest(new BmcTutorialBmcTutorialTC());

		// ////////// brp folder
		smv_suite.addTest(new BrpBrpTC());
		// ////////// ctl-ltl folder
		smv_suite.addTest(new CtlLtlAbp4TC());
		smv_suite.addTest(new CtlLtlAbp8TC());
		smv_suite.addTest(new CtlLtlCounterTC());
		smv_suite.addTest(new CtlLtlDme2TC());
		smv_suite.addTest(new CtlLtlGuidanceTC());
		smv_suite.addTest(new CtlLtlMutexTC());
		smv_suite.addTest(new CtlLtlMutex1TC());
		smv_suite.addTest(new CtlLtlPeriodicTC());
		smv_suite.addTest(new CtlLtlPQueueTC());
		smv_suite.addTest(new CtlLtlProdConsTC());
		smv_suite.addTest(new CtlLtlProductionCellTC());
		// smv_suite.addTest(new CtlLtlQueueTC()); // BUG!!!
		smv_suite.addTest(new CtlLtlRingTC());
		smv_suite.addTest(new CtlLtlSemaphoreTC());
		smv_suite.addTest(new CtlLtlShortTC());
		smv_suite.addTest(new CtlLtlSyncarb5TC());
		// ////////// deadlock folder
		// ////////// example_cmu folder
		// ////////// example_irst folder
		// ////////// guidance folder
		// ////////// hadas folder
		// ////////// msi folder
		// ////////// pci folder
		// ////////// p-queue folder
		// ////////// prod-cons folder
		// ////////// production-cell folder
		// ////////// psl-samples folder
		// ////////// queue folder
		// ////////// rc folder
		// ////////// reactor folder
		// ////////// smv-dist folder
		// ////////// tcas folder
		
		//////////////// known bugs
		smv_suite.addTest(new NotEQBugTC());


		return smv_suite;
	}
}
