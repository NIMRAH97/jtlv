package edu.wis.jtlv.env.module;

import edu.wis.jtlv.env.JTLVBDDCouple;
import edu.wis.jtlv.env.JTLVBDDDefine;

public class SMVModuleParamHolder {
	private SMVModule for_instance;
	private String local_name;
	private String init_string;
	private RedirectTo redirection;

	public SMVModuleParamHolder(SMVModule a_for_instance, String a_local_name,
			String an_init_string) {
		this.for_instance = a_for_instance;
		this.local_name = a_local_name;
		this.init_string = an_init_string;
		this.redirection = null;
	}

	public String getLocalName() {
		return this.local_name;
	}

	public String getInitString() {
		return this.init_string;
	}

	public SMVModule getForInstance() {
		return this.for_instance;
	}

	public void attachPointer() throws ModuleException {
		// if already attached by an early request
		if (this.redirection != null)
			return;
		SMVModule holder_context = this.for_instance.getHolder();
		if (holder_context.hasVar(getInitString(), true)) {
			this.redirection = new RedirectToVar(holder_context.getVar(
					getInitString(), true));
		} else if (holder_context.hasVarArray(getInitString(), true)) {
			this.redirection = new RedirectToVarArray(holder_context
					.getVarArray(getInitString(), true));
		} else if (holder_context.hasDefine(getInitString(), true)) {
			this.redirection = new RedirectToDefine(holder_context.getDefine(
					getInitString(), true));
		} else if (holder_context.hasInstance(getInitString(), true)) {
			this.redirection = new RedirectToInstance(holder_context
					.getInstance(getInitString(), true));
		} else if (holder_context.hasInstanceArray(getInitString(), true)) {
			this.redirection = new RedirectToInstanceArray(holder_context
					.getInstanceArray(getInitString(), true));
		} else {
			// could be an expression...!!
			// for such cases I'm adding a define at this instance with that
			// same name. and attaching this redirection to that define...
			this.for_instance.addDefineFromParam(this.local_name);
			this.redirection = new RedirectToDefine(this.for_instance
					.getDefine(this.local_name, true));
		}
	}

	private boolean attachIfPossible() {
		if (this.redirection == null) {
			try {
				this.attachPointer();
			} catch (ModuleException me) {
				return false;
			}
		}
		return true;
	}

	public boolean isVar() {
		return attachIfPossible() ? this.redirection.isVar() : false;
	}

	public boolean isVarArray() {
		return attachIfPossible() ? this.redirection.isVarArray() : false;
	}

	public boolean isInstance() {
		return attachIfPossible() ? this.redirection.isInstance() : false;
	}

	public boolean isInstanceArray() {
		return attachIfPossible() ? this.redirection.isInstanceArray() : false;
	}

	public boolean isDefine() {
		return attachIfPossible() ? this.redirection.isDefine() : false;
	}

	public JTLVBDDCouple getVar() {
		return attachIfPossible() ? this.redirection.getVar() : null;
	}

	public JTLVBDDCouple[] getVarArray() {
		return attachIfPossible() ? this.redirection.getVarArray() : null;
	}

	public SMVModule getInstance() {
		return attachIfPossible() ? this.redirection.getInstance() : null;
	}

	public SMVModule[] getInstanceArray() {
		return attachIfPossible() ? this.redirection.getInstanceArray() : null;
	}

	public JTLVBDDDefine getDefine() {
		return attachIfPossible() ? this.redirection.getDefine() : null;
	}

	private abstract class RedirectTo {
		protected Object points_to = null;

		protected RedirectTo(Object a_points_to) {
			this.points_to = a_points_to;
		}

		public boolean isVar() {
			return false;
		}

		public boolean isVarArray() {
			return false;
		}

		public boolean isInstance() {
			return false;
		}

		public boolean isInstanceArray() {
			return false;
		}

		public boolean isDefine() {
			return false;
		}

		public JTLVBDDCouple getVar() {
			return null;
		}

		public JTLVBDDCouple[] getVarArray() {
			return null;
		}

		public SMVModule getInstance() {
			return null;
		}

		public SMVModule[] getInstanceArray() {
			return null;
		}

		public JTLVBDDDefine getDefine() {
			return null;
		}

	}

	private class RedirectToVar extends RedirectTo {
		public RedirectToVar(JTLVBDDCouple points_to_var) {
			super(points_to_var);
		}

		@Override
		public boolean isVar() {
			return true;
		}

		@Override
		public JTLVBDDCouple getVar() {
			return (JTLVBDDCouple) super.points_to;
		}
	}

	private class RedirectToVarArray extends RedirectTo {
		public RedirectToVarArray(JTLVBDDCouple[] points_to_var) {
			super(points_to_var);
		}

		@Override
		public boolean isVarArray() {
			return true;
		}

		@Override
		public JTLVBDDCouple[] getVarArray() {
			return (JTLVBDDCouple[]) super.points_to;
		}
	}

	private class RedirectToInstance extends RedirectTo {
		public RedirectToInstance(SMVModule points_to_module) {
			super(points_to_module);
		}

		@Override
		public boolean isInstance() {
			return true;
		}

		@Override
		public SMVModule getInstance() {
			return (SMVModule) super.points_to;
		}
	}

	private class RedirectToInstanceArray extends RedirectTo {
		public RedirectToInstanceArray(SMVModule[] points_to_module) {
			super(points_to_module);
		}

		@Override
		public boolean isInstanceArray() {
			return true;
		}

		@Override
		public SMVModule[] getInstanceArray() {
			return (SMVModule[]) super.points_to;
		}
	}

	private class RedirectToDefine extends RedirectTo {
		public RedirectToDefine(JTLVBDDDefine points_to_define) {
			super(points_to_define);
		}

		@Override
		public boolean isDefine() {
			return true;
		}

		@Override
		public JTLVBDDDefine getDefine() {
			return (JTLVBDDDefine) super.points_to;
		}
	}
}
