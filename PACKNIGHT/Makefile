SHELL=/bin/bash
DOSSIER=Automate
all:
	@for a in $(DOSSIER); do \
		if [ -d $$a ]; then \
		echo "processing folder $$a"; \
		$(MAKE) -C $$a; \
		fi; \
		done;
	@echo "Done!" ]]
clean:
	@for a in $(DOSSIER); do \
		if [ -d $$a ]; then \
		echo "processing folder $$a"; \
		$(MAKE) clean -C $$a; \
		fi; \
		done;
	@echo "Done!" ]]
