AUTOMATES = princesse.auto fm_aleatoire_aveugle.auto packnight.auto fm_berserk.auto 

SUPPR = $(AUTOMATES:.auto=.xml)

all: main $(SUPPR)

# REGLES GENERIQUES #

%.xml: %.auto
	$(EXEC) $<

EXEC =./main.native 

main:
		ocamlbuild -pp "camlp4o.opt -unsafe" -libs graphics main.native	       

clean:
		ocamlbuild -clean
		rm -f *~ $(SUPPR) 
