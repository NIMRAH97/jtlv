package edu.wis.jtlv.new_tb;

import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestSuite;
import edu.wis.jtlv.new_tb.parsing.*;

public class AllTests {

	public static Test suite() {
		// $JUnit-BEGIN$
		TestSuite suite = new TestSuite("Test for JTLV");
		// TestSuite fds_suite = new TestSuite("JTLV tests for fds");
		TestSuite smv_suite = new TestSuite("JTLV tests for smv");
		// suite.addTest(fds_suite);
		suite.addTest(smv_suite);

		// I need to better implement it with reflaction....

		ParsingTestSuite tc000 = new ParsingTestSuite(TC000.class, TC000.file,
				TC000.file);
		ParsingTestSuite tc001 = new ParsingTestSuite(TC001.class, TC001.file,
				TC001.file);
		ParsingTestSuite tc002 = new ParsingTestSuite(TC002.class, TC002.file,
				TC002.file);
		ParsingTestSuite tc003 = new ParsingTestSuite(TC003.class, TC003.file,
				TC003.file);
		ParsingTestSuite tc004 = new ParsingTestSuite(TC004.class, TC004.file,
				TC004.file);
		ParsingTestSuite tc005 = new ParsingTestSuite(TC005.class, TC005.file,
				TC005.file);
		ParsingTestSuite tc006 = new ParsingTestSuite(TC006.class, TC006.file,
				TC006.file);
		ParsingTestSuite tc007 = new ParsingTestSuite(TC007.class, TC007.file,
				TC007.file);
		ParsingTestSuite tc008 = new ParsingTestSuite(TC008.class, TC008.file,
				TC008.file);
		ParsingTestSuite tc009 = new ParsingTestSuite(TC009.class, TC009.file,
				TC009.file);
		ParsingTestSuite tc010 = new ParsingTestSuite(TC010.class, TC010.file,
				TC010.file);
		ParsingTestSuite tc011 = new ParsingTestSuite(TC011.class, TC011.file,
				TC011.file);
		ParsingTestSuite tc012 = new ParsingTestSuite(TC012.class, TC012.file,
				TC012.file);
		ParsingTestSuite tc013 = new ParsingTestSuite(TC013.class, TC013.file,
				TC013.file);
		ParsingTestSuite tc014 = new ParsingTestSuite(TC014.class, TC014.file,
				TC014.file);
		ParsingTestSuite tc015 = new ParsingTestSuite(TC015.class, TC015.file,
				TC015.file);
		ParsingTestSuite tc016 = new ParsingTestSuite(TC016.class, TC016.file,
				TC016.file);
		ParsingTestSuite tc017 = new ParsingTestSuite(TC017.class, TC017.file,
				TC017.file);
		ParsingTestSuite tc018 = new ParsingTestSuite(TC018.class, TC018.file,
				TC018.file);
		ParsingTestSuite tc019 = new ParsingTestSuite(TC019.class, TC019.file,
				TC019.file);
		ParsingTestSuite tc020 = new ParsingTestSuite(TC020.class, TC020.file,
				TC020.file);
		ParsingTestSuite tc021 = new ParsingTestSuite(TC021.class, TC021.file,
				TC021.file);
		ParsingTestSuite tc022 = new ParsingTestSuite(TC022.class, TC022.file,
				TC022.file);
		ParsingTestSuite tc023 = new ParsingTestSuite(TC023.class, TC023.file,
				TC023.file);
		ParsingTestSuite tc024 = new ParsingTestSuite(TC024.class, TC024.file,
				TC024.file);
		ParsingTestSuite tc025 = new ParsingTestSuite(TC025.class, TC025.file,
				TC025.file);
		ParsingTestSuite tc026 = new ParsingTestSuite(TC026.class, TC026.file,
				TC026.file);
		ParsingTestSuite tc027 = new ParsingTestSuite(TC027.class, TC027.file,
				TC027.file);
		ParsingTestSuite tc028 = new ParsingTestSuite(TC028.class, TC028.file,
				TC028.file);
		ParsingTestSuite tc029 = new ParsingTestSuite(TC029.class, TC029.file,
				TC029.file);

		ParsingTestSuite tc030 = new ParsingTestSuite(TC030.class, TC030.file,
				TC030.file);
		ParsingTestSuite tc031 = new ParsingTestSuite(TC031.class, TC031.file,
				TC031.file);
		ParsingTestSuite tc032 = new ParsingTestSuite(TC032.class, TC032.file,
				TC032.file);
		ParsingTestSuite tc033 = new ParsingTestSuite(TC033.class, TC033.file,
				TC033.file);
		ParsingTestSuite tc034 = new ParsingTestSuite(TC034.class, TC034.file,
				TC034.file);
		ParsingTestSuite tc035 = new ParsingTestSuite(TC035.class, TC035.file,
				TC035.file);
		ParsingTestSuite tc036 = new ParsingTestSuite(TC036.class, TC036.file,
				TC036.file);
		ParsingTestSuite tc037 = new ParsingTestSuite(TC037.class, TC037.file,
				TC037.file);
		ParsingTestSuite tc038 = new ParsingTestSuite(TC038.class, TC038.file,
				TC038.file);
		ParsingTestSuite tc039 = new ParsingTestSuite(TC039.class, TC039.file,
				TC039.file);

		ParsingTestSuite tc040 = new ParsingTestSuite(TC040.class, TC040.file,
				TC040.file);
		ParsingTestSuite tc041 = new ParsingTestSuite(TC041.class, TC041.file,
				TC041.file);
		ParsingTestSuite tc042 = new ParsingTestSuite(TC042.class, TC042.file,
				TC042.file);
		ParsingTestSuite tc043 = new ParsingTestSuite(TC043.class, TC043.file,
				TC043.file);
		ParsingTestSuite tc044 = new ParsingTestSuite(TC044.class, TC044.file,
				TC044.file);
		ParsingTestSuite tc045 = new ParsingTestSuite(TC045.class, TC045.file,
				TC045.file);
		ParsingTestSuite tc046 = new ParsingTestSuite(TC046.class, TC046.file,
				TC046.file);
		ParsingTestSuite tc047 = new ParsingTestSuite(TC047.class, TC047.file,
				TC047.file);
		ParsingTestSuite tc048 = new ParsingTestSuite(TC048.class, TC048.file,
				TC048.file);
		ParsingTestSuite tc049 = new ParsingTestSuite(TC049.class, TC049.file,
				TC049.file);

		ParsingTestSuite tc050 = new ParsingTestSuite(TC050.class, TC050.file,
				TC050.file);
		ParsingTestSuite tc051 = new ParsingTestSuite(TC051.class, TC051.file,
				TC051.file);
		ParsingTestSuite tc052 = new ParsingTestSuite(TC052.class, TC052.file,
				TC052.file);
		ParsingTestSuite tc053 = new ParsingTestSuite(TC053.class, TC053.file,
				TC053.file);
		ParsingTestSuite tc054 = new ParsingTestSuite(TC054.class, TC054.file,
				TC054.file);
		ParsingTestSuite tc055 = new ParsingTestSuite(TC055.class, TC055.file,
				TC055.file);
		ParsingTestSuite tc056 = new ParsingTestSuite(TC056.class, TC056.file,
				TC056.file);
		ParsingTestSuite tc057 = new ParsingTestSuite(TC057.class, TC057.file,
				TC057.file);
		ParsingTestSuite tc058 = new ParsingTestSuite(TC058.class, TC058.file,
				TC058.file);
		ParsingTestSuite tc059 = new ParsingTestSuite(TC059.class, TC059.file,
				TC059.file);

		ParsingTestSuite tc060 = new ParsingTestSuite(TC060.class, TC060.file,
				TC060.file);
		ParsingTestSuite tc061 = new ParsingTestSuite(TC061.class, TC061.file,
				TC061.file);
		ParsingTestSuite tc062 = new ParsingTestSuite(TC062.class, TC062.file,
				TC062.file);
		ParsingTestSuite tc063 = new ParsingTestSuite(TC063.class, TC063.file,
				TC063.file);
		ParsingTestSuite tc064 = new ParsingTestSuite(TC064.class, TC064.file,
				TC064.file);
		ParsingTestSuite tc065 = new ParsingTestSuite(TC065.class, TC065.file,
				TC065.file);
		ParsingTestSuite tc066 = new ParsingTestSuite(TC066.class, TC066.file,
				TC066.file);
		ParsingTestSuite tc067 = new ParsingTestSuite(TC067.class, TC067.file,
				TC067.file);
		ParsingTestSuite tc068 = new ParsingTestSuite(TC068.class, TC068.file,
				TC068.file);
		ParsingTestSuite tc069 = new ParsingTestSuite(TC069.class, TC069.file,
				TC069.file);

		ParsingTestSuite tc070 = new ParsingTestSuite(TC070.class, TC070.file,
				TC070.file);
		ParsingTestSuite tc071 = new ParsingTestSuite(TC071.class, TC071.file,
				TC071.file);
		ParsingTestSuite tc072 = new ParsingTestSuite(TC072.class, TC072.file,
				TC072.file);
		ParsingTestSuite tc073 = new ParsingTestSuite(TC073.class, TC073.file,
				TC073.file);
		ParsingTestSuite tc074 = new ParsingTestSuite(TC074.class, TC074.file,
				TC074.file);
		ParsingTestSuite tc075 = new ParsingTestSuite(TC075.class, TC075.file,
				TC075.file);
		ParsingTestSuite tc076 = new ParsingTestSuite(TC076.class, TC076.file,
				TC076.file);
		ParsingTestSuite tc077 = new ParsingTestSuite(TC077.class, TC077.file,
				TC077.file);
		ParsingTestSuite tc078 = new ParsingTestSuite(TC078.class, TC078.file,
				TC078.file);
		ParsingTestSuite tc079 = new ParsingTestSuite(TC079.class, TC079.file,
				TC079.file);

		ParsingTestSuite tc080 = new ParsingTestSuite(TC080.class, TC080.file,
				TC080.file);
		ParsingTestSuite tc081 = new ParsingTestSuite(TC081.class, TC081.file,
				TC081.file);
		ParsingTestSuite tc082 = new ParsingTestSuite(TC082.class, TC082.file,
				TC082.file);
		ParsingTestSuite tc083 = new ParsingTestSuite(TC083.class, TC083.file,
				TC083.file);
		ParsingTestSuite tc084 = new ParsingTestSuite(TC084.class, TC084.file,
				TC084.file);
		ParsingTestSuite tc085 = new ParsingTestSuite(TC085.class, TC085.file,
				TC085.file);
		ParsingTestSuite tc086 = new ParsingTestSuite(TC086.class, TC086.file,
				TC086.file);
		ParsingTestSuite tc087 = new ParsingTestSuite(TC087.class, TC087.file,
				TC087.file);
		ParsingTestSuite tc088 = new ParsingTestSuite(TC088.class, TC088.file,
				TC088.file);
		ParsingTestSuite tc089 = new ParsingTestSuite(TC089.class, TC089.file,
				TC089.file);

		ParsingTestSuite tc090 = new ParsingTestSuite(TC090.class, TC090.file,
				TC090.file);
		ParsingTestSuite tc091 = new ParsingTestSuite(TC091.class, TC091.file,
				TC091.file);
		ParsingTestSuite tc092 = new ParsingTestSuite(TC092.class, TC092.file,
				TC092.file);
		ParsingTestSuite tc093 = new ParsingTestSuite(TC093.class, TC093.file,
				TC093.file);
		ParsingTestSuite tc094 = new ParsingTestSuite(TC094.class, TC094.file,
				TC094.file);
		ParsingTestSuite tc095 = new ParsingTestSuite(TC095.class, TC095.file,
				TC095.file);
		ParsingTestSuite tc096 = new ParsingTestSuite(TC096.class, TC096.file,
				TC096.file);
		ParsingTestSuite tc097 = new ParsingTestSuite(TC097.class, TC097.file,
				TC097.file);
		ParsingTestSuite tc098 = new ParsingTestSuite(TC098.class, TC098.file,
				TC098.file);
		ParsingTestSuite tc099 = new ParsingTestSuite(TC099.class, TC099.file,
				TC099.file);

		ParsingTestSuite tc100 = new ParsingTestSuite(TC100.class, TC100.file,
				TC100.file);
		ParsingTestSuite tc101 = new ParsingTestSuite(TC101.class, TC101.file,
				TC101.file);
		ParsingTestSuite tc102 = new ParsingTestSuite(TC102.class, TC102.file,
				TC102.file);
		ParsingTestSuite tc103 = new ParsingTestSuite(TC103.class, TC103.file,
				TC103.file);
		ParsingTestSuite tc104 = new ParsingTestSuite(TC104.class, TC104.file,
				TC104.file);
		ParsingTestSuite tc105 = new ParsingTestSuite(TC105.class, TC105.file,
				TC105.file);
		ParsingTestSuite tc106 = new ParsingTestSuite(TC106.class, TC106.file,
				TC106.file);
		ParsingTestSuite tc107 = new ParsingTestSuite(TC107.class, TC107.file,
				TC107.file);
		ParsingTestSuite tc108 = new ParsingTestSuite(TC108.class, TC108.file,
				TC108.file);
		ParsingTestSuite tc109 = new ParsingTestSuite(TC109.class, TC109.file,
				TC109.file);

		ParsingTestSuite tc110 = new ParsingTestSuite(TC110.class, TC110.file,
				TC110.file);

		// ParsingTestSuite tc0xx0 = new ParsingTestSuite(TC0xx0.class,
		// TC0xx0.name, TC0xx0.file);
		// ParsingTestSuite tc0xx1 = new ParsingTestSuite(TC0xx1.class,
		// TC0xx1.name, TC0xx1.file);
		// ParsingTestSuite tc0xx2 = new ParsingTestSuite(TC0xx2.class,
		// TC0xx2.name, TC0xx2.file);
		// ParsingTestSuite tc0xx3 = new ParsingTestSuite(TC0xx3.class,
		// TC0xx3.name, TC0xx3.file);
		// ParsingTestSuite tc0xx4 = new ParsingTestSuite(TC0xx4.class,
		// TC0xx4.name, TC0xx4.file);
		// ParsingTestSuite tc0xx5 = new ParsingTestSuite(TC0xx5.class,
		// TC0xx5.name, TC0xx5.file);
		// ParsingTestSuite tc0xx6 = new ParsingTestSuite(TC0xx6.class,
		// TC0xx6.name, TC0xx6.file);
		// ParsingTestSuite tc0xx7 = new ParsingTestSuite(TC0xx7.class,
		// TC0xx7.name, TC0xx7.file);
		// ParsingTestSuite tc0xx8 = new ParsingTestSuite(TC0xx8.class,
		// TC0xx8.name, TC0xx8.file);
		// ParsingTestSuite tc0xx9 = new ParsingTestSuite(TC0xx9.class,
		// TC0xx9.name, TC0xx9.file);

		smv_suite.addTest(tc000);
		smv_suite.addTest(tc001);
		smv_suite.addTest(tc002);
		smv_suite.addTest(tc003);
		smv_suite.addTest(tc004);
		smv_suite.addTest(tc005);
		smv_suite.addTest(tc006);
		smv_suite.addTest(tc007);
		smv_suite.addTest(tc008);
		smv_suite.addTest(tc009);
		smv_suite.addTest(tc010);
		smv_suite.addTest(tc011);
		smv_suite.addTest(tc012);
		smv_suite.addTest(tc013);
		smv_suite.addTest(tc014);
		smv_suite.addTest(tc015);
		smv_suite.addTest(tc016);
		smv_suite.addTest(tc017);
		smv_suite.addTest(tc018);
		smv_suite.addTest(tc019);
		smv_suite.addTest(tc020);
		smv_suite.addTest(tc021);
		smv_suite.addTest(tc022);
		smv_suite.addTest(tc023);
		smv_suite.addTest(tc024);
		smv_suite.addTest(tc025);
		smv_suite.addTest(tc026);
		smv_suite.addTest(tc027);
		smv_suite.addTest(tc028);
		smv_suite.addTest(tc029);

		smv_suite.addTest(tc030);
		smv_suite.addTest(tc031);
		smv_suite.addTest(tc032);
		smv_suite.addTest(tc033);
		smv_suite.addTest(tc034);
		smv_suite.addTest(tc035);
		smv_suite.addTest(tc036);
		smv_suite.addTest(tc037);
		smv_suite.addTest(tc038);
		smv_suite.addTest(tc039);

		smv_suite.addTest(tc040);
		smv_suite.addTest(tc041);
		smv_suite.addTest(tc042);
		smv_suite.addTest(tc043);
		smv_suite.addTest(tc044);
		smv_suite.addTest(tc045);
		smv_suite.addTest(tc046);
		smv_suite.addTest(tc047);
		smv_suite.addTest(tc048);
		smv_suite.addTest(tc049);

		smv_suite.addTest(tc050);
		smv_suite.addTest(tc051);
		smv_suite.addTest(tc052);
		smv_suite.addTest(tc053);
		smv_suite.addTest(tc054);
		smv_suite.addTest(tc055);
		smv_suite.addTest(tc056);
		smv_suite.addTest(tc057);
		smv_suite.addTest(tc058);
		smv_suite.addTest(tc059);

		smv_suite.addTest(tc060);
		smv_suite.addTest(tc061);
		smv_suite.addTest(tc062);
		smv_suite.addTest(tc063);
		smv_suite.addTest(tc064);
		smv_suite.addTest(tc065);
		smv_suite.addTest(tc066);
		smv_suite.addTest(tc067);
		smv_suite.addTest(tc068);
		smv_suite.addTest(tc069);

		smv_suite.addTest(tc070);
		smv_suite.addTest(tc071);
		smv_suite.addTest(tc072);
		smv_suite.addTest(tc073);
		smv_suite.addTest(tc074);
		smv_suite.addTest(tc075);
		smv_suite.addTest(tc076);
		smv_suite.addTest(tc077);
		smv_suite.addTest(tc078);
		smv_suite.addTest(tc079);

		smv_suite.addTest(tc080);
		smv_suite.addTest(tc081);
		smv_suite.addTest(tc082);
		smv_suite.addTest(tc083);
		smv_suite.addTest(tc084);
		smv_suite.addTest(tc085);
		smv_suite.addTest(tc086);
		smv_suite.addTest(tc087);
		smv_suite.addTest(tc088);
		smv_suite.addTest(tc089);

		smv_suite.addTest(tc090);
		smv_suite.addTest(tc091);
		smv_suite.addTest(tc092);
		smv_suite.addTest(tc093);
		smv_suite.addTest(tc094);
		smv_suite.addTest(tc095);
		smv_suite.addTest(tc096);
		smv_suite.addTest(tc097);
		smv_suite.addTest(tc098);
		smv_suite.addTest(tc099);

		smv_suite.addTest(tc100);
		smv_suite.addTest(tc101);
		smv_suite.addTest(tc102);
		smv_suite.addTest(tc103);
		smv_suite.addTest(tc104);
		smv_suite.addTest(tc105);
		smv_suite.addTest(tc106);
		smv_suite.addTest(tc107);
		smv_suite.addTest(tc108);
		smv_suite.addTest(tc109);

		smv_suite.addTest(tc110);

		// smv_suite.addTest(tc0xx0);
		// smv_suite.addTest(tc0xx1);
		// smv_suite.addTest(tc0xx2);
		// smv_suite.addTest(tc0xx3);
		// smv_suite.addTest(tc0xx4);
		// smv_suite.addTest(tc0xx5);
		// smv_suite.addTest(tc0xx6);
		// smv_suite.addTest(tc0xx7);
		// smv_suite.addTest(tc0xx8);
		// smv_suite.addTest(tc0xx9);

		// $JUnit-END$

		return suite;
	}

	public static Vector<String> get_all_file_name() {
		Vector<String> ret = new Vector<String>(100);
		// ret.add("testcases/legacy/mutex1.smv");
		//
		// ret.add("testcases/legacy/abp/abp10.smv");
		// ret.add("testcases/legacy/abp/abp11.smv");
		// ret.add("testcases/legacy/abp/abp16.smv");
		// ret.add("testcases/legacy/abp/abp4.smv");
		// ret.add("testcases/legacy/abp/abp8.smv");
		// ret.add("testcases/legacy/bmc/MCP.smv");
		// ret.add("testcases/legacy/bmc/barrel5.smv");
		// ret.add("testcases/legacy/bmc/counter.smv");
		// ret.add("testcases/legacy/bmc/dme/dme10.smv");
		// ret.add("testcases/legacy/bmc/dme/dme11.smv");
		// ret.add("testcases/legacy/bmc/dme/dme12.smv");
		// ret.add("testcases/legacy/bmc/dme/dme13.smv");
		// ret.add("testcases/legacy/bmc/dme/dme14.smv");
		// ret.add("testcases/legacy/bmc/dme/dme15.smv");
		// ret.add("testcases/legacy/bmc/dme/dme16.smv");
		// ret.add("testcases/legacy/bmc/dme/dme17.smv");
		// ret.add("testcases/legacy/bmc/dme/dme18.smv");
		// ret.add("testcases/legacy/bmc/dme/dme19.smv");
		// ret.add("testcases/legacy/bmc/dme/dme20.smv");
		// ret.add("testcases/legacy/bmc/dme/dme30.smv");
		// ret.add("testcases/legacy/bmc/dme/dme4.smv");
		// ret.add("testcases/legacy/bmc/dme/dme40.smv");
		// ret.add("testcases/legacy/bmc/dme/dme5.smv");
		// ret.add("testcases/legacy/bmc/dme/dme50.smv");
		// ret.add("testcases/legacy/bmc/dme/dme6.smv");
		// ret.add("testcases/legacy/bmc/dme/dme7.smv");
		// ret.add("testcases/legacy/bmc/dme/dme8.smv");
		// ret.add("testcases/legacy/bmc/dme/dme9.smv");
		// ret.add("testcases/legacy/bmc/multiplier.smv");
		// ret.add("testcases/legacy/bmc_tutorial/bmc_tutorial.smv");

		// /////////////////////////////////////////////////////////////////

		// ret.add("testcases/legacy/brp/brp.smv"); // this test is making a problem
		// ret.add("testcases/legacy/ctl-ltl/abp4.smv");
		// ret.add("testcases/legacy/ctl-ltl/abp8.smv");
		// ret.add("testcases/legacy/ctl-ltl/counter.smv");
		// ret.add("testcases/legacy/ctl-ltl/dme2.smv");
		// ret.add("testcases/legacy/ctl-ltl/guidance.smv");
		// ret.add("testcases/legacy/ctl-ltl/mutex.smv");
		// ret.add("testcases/legacy/ctl-ltl/mutex1.smv");
		// ret.add("testcases/legacy/ctl-ltl/p-queue.smv");
		// ret.add("testcases/legacy/ctl-ltl/periodic.smv"); // TC040
		// ret.add("testcases/legacy/ctl-ltl/prod-cons.smv");
		// ret.add("testcases/legacy/ctl-ltl/production-cell.smv");
		// ret.add("testcases/legacy/ctl-ltl/queue.smv");
		// ret.add("testcases/legacy/ctl-ltl/ring.smv");
		// ret.add("testcases/legacy/ctl-ltl/semaphore.smv");
		// ret.add("testcases/legacy/ctl-ltl/short.smv");
		// ret.add("testcases/legacy/ctl-ltl/syncarb5.smv"); // TC047

		// /////////////////////////////////

		// ret.add("testcases/legacy/deadlock/dartes.smv"); // TC048
		// ret.add("testcases/legacy/deadlock/dpd75.smv"); // TC049
		// ret.add("testcases/legacy/deadlock/dph35.smv"); // TC050
		// ret.add("testcases/legacy/deadlock/ftp3.smv"); // TC051
		// ret.add("testcases/legacy/deadlock/furnace17.smv"); // TC052
		// ret.add("testcases/legacy/deadlock/gas-nq7.smv");
		// ret.add("testcases/legacy/deadlock/key10.smv");
		// ret.add("testcases/legacy/deadlock/mmgt20.smv");
		// ret.add("testcases/legacy/deadlock/over12.smv"); // TC056

		// ret.add("testcases/legacy/example_cmu/counter.smv");
		// ret.add("testcases/legacy/example_cmu/dme1.smv");
		// ret.add("testcases/legacy/example_cmu/dme2.smv");
		// ret.add("testcases/legacy/example_cmu/gigamax.smv"); //TC060
		// ret.add("testcases/legacy/example_cmu/mutex.smv");
		// ret.add("testcases/legacy/example_cmu/mutex1.smv");
		// ret.add("testcases/legacy/example_cmu/periodic.smv"); // TC063
		// ret.add("testcases/legacy/example_cmu/ring.smv");
		// ret.add("testcases/legacy/example_cmu/semaphore.smv");
		// ret.add("testcases/legacy/example_cmu/short.smv");
		// ret.add("testcases/legacy/example_cmu/syncarb5.smv"); // TC067

		// ret.add("testcases/legacy/example_irst/dme4.smv");
		// ret.add("testcases/legacy/example_irst/dme5.smv");
		// ret.add("testcases/legacy/example_irst/dme6.smv"); //TC070
		// ret.add("testcases/legacy/example_irst/gigamax.smv");
		// ret.add("testcases/legacy/example_irst/gigamax_ltl.smv");
		// ret.add("testcases/legacy/example_irst/periodic.smv");

		// ret.add("testcases/legacy/guidance/guidance.smv");
		// ret.add("testcases/legacy/msi/msi_wtrans.smv");

		// ret.add("testcases/legacy/p-queue/p-queue.smv");
		// ret.add("testcases/legacy/pci/pci.smv"); // TC077
		// ret.add("testcases/legacy/prod-cons/prod-cons.smv"); // TC078
		// ret.add("testcases/legacy/production-cell/production-cell.smv");

		// ret.add("testcases/legacy/psl-samples/counter.smv"); // TC080
		// ret.add("testcases/legacy/psl-samples/dme2-16.smv");
		// ret.add("testcases/legacy/psl-samples/dme2.smv");
		// ret.add("testcases/legacy/psl-samples/gigamax.smv");
		// ret.add("testcases/legacy/psl-samples/ring.smv");
		// ret.add("testcases/legacy/psl-samples/semaphore.smv");
		// ret.add("testcases/legacy/psl-samples/short.smv");
		// ret.add("testcases/legacy/psl-samples/syncarb10.smv"); // TC087
		// ret.add("testcases/legacy/psl-samples/syncarb5.smv"); // TC088

		// ret.add("testcases/legacy/queue/queue.smv");
		// ret.add("testcases/legacy/reactor/base.smv"); // TC090
		// ret.add("testcases/legacy/reactor/idle.smv"); 

		// /////////////////////////////////

		// ret.add("testcases/legacy/smv-dist/counter.smv");
		// ret.add("testcases/legacy/smv-dist/dme1-16.smv");
		// ret.add("testcases/legacy/smv-dist/dme1.smv");
		// ret.add("testcases/legacy/smv-dist/dme2-16.smv");
		// ret.add("testcases/legacy/smv-dist/dme2.smv");
		// ret.add("testcases/legacy/smv-dist/gigamax.smv");
		// ret.add("testcases/legacy/smv-dist/mutex.smv");
		// ret.add("testcases/legacy/smv-dist/mutex1.smv");
		// ret.add("testcases/legacy/smv-dist/pci3p.smv"); // TC100
		// ret.add("testcases/legacy/smv-dist/pci4p.smv"); 
		// ret.add("testcases/legacy/smv-dist/periodic.smv"); // TC102
		// ret.add("testcases/legacy/smv-dist/ring.smv");
		// ret.add("testcases/legacy/smv-dist/robot.smv");
		// ret.add("testcases/legacy/smv-dist/semaphore.smv"); // TC105
		// ret.add("testcases/legacy/smv-dist/short.smv"); 
		// ret.add("testcases/legacy/smv-dist/syncarb10.smv");
		// ret.add("testcases/legacy/smv-dist/syncarb5.smv");

		// /////////////////////////////////

		// ret.add("testcases/legacy/tcas/tcas-t.smv"); // TC109
		// ret.add("testcases/legacy/tcas/tcas.smv");

		return ret;
	}
}
