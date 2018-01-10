# Topic Extraction and Alignment for Large Scientific Document Collections

## Project - Thèse

[ARESOS - CNRS Mastodons](http://www.cnrs.fr/mi/IMG/pdf/2014-01-23-mastodons-_aresos_-_journee_mastodons.pdf) -> [EPIQUE : Reconstruire l'évolution des sciences à grande échelle](http://www-bd.lip6.fr/wiki/site/recherche/projets/epique/start)<br />

*Goal:* Notre objectif est de développer des solutions performantes pour générer et interagir avec des cartes phylomémétiques qui exploitent les avancées technologiques récentes pour la parallélisation des tâches et des algorithmes sur des données complexes et volumineuses.

*Keywords:* épistémologie quantitative, évolution des sciences, détéction de topics, alignement temporel, traitements de données à large échelle, sciences des données, big data, scientométrie

*Tool:* La plateforme de calcul Apache Spark du LIP6 (déployée sur 16 machines totalisant plus de 300 cœurs et 800GB de RAM)

*Data:* L'ensemble des publications scientifiques de MedLine (médecine), HAL (informatique) et RePEc (économie). 

### Useful Links

*Toy Data:* <br /> 
[1] https://archive.org/details/stackexchange<br />
[2] https://www.reddit.com/r/datasets/comments/3bxlg7/i_have_every_publicly_available_reddit_comment/<br />

*Test Environment:* <br />
[How-to: Create a Simple Hadoop Cluster with VirtualBox](https://blog.cloudera.com/blog/2014/01/how-to-create-a-simple-hadoop-cluster-with-virtualbox/)

*Spark LDA:* <br />
[MLlib Clustering - LDA](https://spark.apache.org/docs/2.2.0/mllib-clustering.html#latent-dirichlet-allocation-lda)<br />
[Large Scale Topic Modeling: Improvements to LDA on Apache Spark](https://databricks.com/blog/2015/09/22/large-scale-topic-modeling-improvements-to-lda-on-apache-spark.html)<br />
[Topic modeling with LDA: MLlib meets GraphX](https://databricks.com/blog/2015/03/25/topic-modeling-with-lda-mllib-meets-graphx.html)<br />
[spectral LDA algorithm - Jira](https://issues.apache.org/jira/browse/SPARK-18599)<br />

*Others:* <br />
[Spark Packages](https://spark-packages.org/)<br />
[FPGrowth Frequent Pattern Mining](https://spark.apache.org/docs/latest/mllib-frequent-pattern-mining.html)
[FPGrowth Algorithm](http://www.philippe-fournier-viger.com/spmf/FPGrowth.php)

## Stage(Lié à la thèse)

Ce stage a pour objectif principal de définir et de réaliser un workflow interactif pour extraire des domaines scientifiques à partir d’une collection de documents. Le travail à effectuer est le suivant:
    
    * état de l’art sur les modèles d’extraction de topics et analyse de leurs propriétés (parallélisation, incrémentalité)
    * définition d’un workflow incrémental pour l’extraction et l’alignement de topics
    * implémentation du workflow
    * validation expérimentale



## References

[1] Chavalarias D, Cointet J-P (2013) Phylomemetic Patterns in Science Evolution-The Rise and Fall of Scientific Fields. PLoS ONE 8(2): e54847. doi:10.1371/journal.pone.0054847<br />
[2] A.. Rajaraman, J. Leskovec, J. D. Ullman. Mining of Massive Datasets 2013, http://infolab.stanford.edu/~ullman/mmds/book.pdf<br />
[3] Kyuseok Shim. MapReduce Algorithms for Big Data Analysis, Tutorial (VLDB12, SWCW13)<br />
[4] S. Yang et. al. Efficient Dense Structure Mining using MapReduce, IEEE International Conference on DataMining Workshops, 2009<br />
[5] S. Papadimitriou and J. Sun. DisCo: Distributed Co-clustering with Map-Reduce: A Case Study towards Petabyte-Scale End-to-End Mining. In IEEE Intl Conf.on Data Mining (ICDM), 2008<br />
[6] R. M. C. McCreadie, C. Macdonald, and L. Ounis. On single-pass indexing with MapReduce. In ACM Conf. on Research and development in information retrieval (SIGIR), 2009<br />
[7] D. Fried, S. G. Kobourov: Maps of Computer Science. PacificVis 2014: 113-120, http://mocs.cs.arizona.edu, 2014<br />
[8] M. I. Hossain, S. G. Kobourov: Research Topics Map: RTopMap. http://rtopmap.arl.arizona.edu, https://arxiv.org/pdf/1706.04979.pdf, 2017<br />
[9] J. D. Ullman, Designing good MapReduce algorithms, XRDS: Crossroads, The ACM Magazine for Students 19(1), 2012<br />
