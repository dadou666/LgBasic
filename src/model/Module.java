package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import semantique.InitModuleRef;

public class Module {
	public boolean estAPI;
	public List<FonctionDef> fonctions = new ArrayList<FonctionDef>();
	public List<TypeDef> types = new ArrayList<TypeDef>();

	public void initNomModule(String nom) {
		InitModuleRef init = new InitModuleRef();
		init.module = nom;
		for (FonctionDef fd : fonctions) {
			for (Var var : fd.params) {
				if (var.type.module == null) {
					var.type.module = nom;
					var.type.moduleInit = true;
				}
			}
			if (fd.expression != null) {

				fd.expression.visiter(init);
			} else {
				if (fd.typeRetour.module == null) {
					fd.typeRetour.moduleInit = true;
					fd.typeRetour.module = nom;
				}
			}
		}
		for (TypeDef td : types) {
			if (td.superType != null && td.superType.module == null) {
				td.superType.module = nom;
				td.superType.moduleInit = true;

			}
			for (Var var : td.vars) {
				if (var.type.module == null) {
					var.type.module = nom;
					var.type.moduleInit = true;
				}
			}
		}
	}

}
