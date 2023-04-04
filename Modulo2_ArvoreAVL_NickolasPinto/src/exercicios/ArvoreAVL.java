package exercicios;

public class ArvoreAVL {
	
    private Node raiz;

    public ArvoreAVL() {
        this.raiz = null;
    }

    public Node getRaiz() {
        return raiz;
    }

    public void inserir(int chave) {
    	System.out.println("\nSolicitada a inserção da chave: " + chave);
        this.raiz = inserirRecursivo(this.raiz, chave);
    }

    private Node inserirRecursivo(Node atual, int chave) {
        if (atual == null) {
        	System.out.println("\nA chave " + chave + " foi inserida com sucesso!");
            return new Node(chave);
        }

        if (chave < atual.getChave()) {
            atual.setEsquerda(inserirRecursivo(atual.getEsquerda(), chave));
        } else if (chave > atual.getChave()) {
            atual.setDireita(inserirRecursivo(atual.getDireita(), chave));
        } else {
        	System.out.println("\nA chave " + chave + " já existe, portanto não foi inserida...");
            return atual;
        }
        
        atual.setAltura(ajustaAltura(atual));

        return balancearArvoreAVL(atual);
    }
    
    public void printarArvore() {
    	System.out.print("\n");
        printarArvoreRecursivo(raiz);
        System.out.print("\n");
    }

    private void printarArvoreRecursivo(Node atual) {
        if (atual == null) {
            return;
        }

        System.out.print("(");
        System.out.print(atual.getChave());
        printarArvoreRecursivo(atual.getEsquerda());
        printarArvoreRecursivo(atual.getDireita());
        System.out.print(")");
    }
    
    public int ajustaAltura(Node no) {
    	return 1 + Math.max(getAltura(no.getEsquerda()), getAltura(no.getDireita()));
    }
    
    public void excluir(int chave) {
    	System.out.println("\nSolicitada a exclusão da chave: " + chave);
        this.raiz = excluirRecursivo(raiz, chave);
     }
    
    private Node excluirRecursivo(Node atual, int chave) {
    	boolean encontrou = false;
        if (atual == null) {
        	 System.out.println("A chave " + chave + " não existe na árvore, portanto a exclusão não foi possível...");
             return null;
        }

        if (chave < atual.getChave()) {
            atual.setEsquerda(excluirRecursivo(atual.getEsquerda(), chave));
        } else if (chave > atual.getChave()) {
            atual.setDireita(excluirRecursivo(atual.getDireita(), chave));
        } else {
        	encontrou = true;
            if (atual.getEsquerda() == null || atual.getDireita() == null) {
                Node temporario = null;
                if (temporario == atual.getEsquerda()) {
                    temporario = atual.getDireita();
                } else {
                    temporario = atual.getEsquerda();
                }

                if (temporario == null) {
                    temporario = atual;
                    atual = null;
                } else {
                    atual = temporario;
                }
            } else {
                Node temporario = procurarNodeDeMenorValorParaExcluir(atual.getEsquerda());
                atual.setChave(temporario.getChave());
                atual.setEsquerda(excluirRecursivo(atual.getEsquerda(), temporario.getChave()));
            }
        }

        if (atual == null) {
            return atual;
        }
        
        atual.setAltura(ajustaAltura(atual));
        
        if (encontrou)
         	System.out.println("Chave " + chave + " encontrada e excluída com sucesso!");
        
        return balancearArvoreAVL(atual);
    }
        
        private Node procurarNodeDeMenorValorParaExcluir(Node atual) {
            Node menorAtual = atual;

            while (menorAtual.getDireita() != null) {
            	menorAtual = menorAtual.getDireita();
            }

            return menorAtual;
        }
        
        private Node rotacionarDireita(Node atual) {
            Node filhoEsquerdaDoAtual = atual.getEsquerda();
            Node filhoDireitaDoFilhoDoAtual = filhoEsquerdaDoAtual.getDireita();
            
            filhoEsquerdaDoAtual.setDireita(atual);
            atual.setEsquerda(filhoDireitaDoFilhoDoAtual);
            
            atual.setAltura(ajustaAltura(atual));
            filhoEsquerdaDoAtual.setAltura(ajustaAltura(filhoEsquerdaDoAtual));
            
            return filhoEsquerdaDoAtual;
        }

        private Node rotacionarEsquerda(Node atual) {
            Node filhoDireitaDoAtual = atual.getDireita();
            Node filhoEsquerdaDoFilhoDoAtual = filhoDireitaDoAtual.getEsquerda();
            
            filhoDireitaDoAtual.setEsquerda(atual);
            atual.setDireita(filhoEsquerdaDoFilhoDoAtual);
            
            atual.setAltura(ajustaAltura(atual));
            filhoDireitaDoAtual.setAltura(ajustaAltura(filhoDireitaDoAtual));
            
            return filhoDireitaDoAtual;
        }
        
        public Node balancearArvoreAVL(Node atual) {
        	 int balancementoAtual = getBalanceamento(atual);
             int balancementoAtualFilhoDireita = getBalanceamento(atual.getDireita());
             int balancementoAtualFilhoEsquerda = getBalanceamento(atual.getEsquerda());
             
             // rotação simples a esquerda
             if (balancementoAtual < -1 && balancementoAtualFilhoDireita == -1) {
             	System.out.println("\nRotação a esquerda no nó: " + atual.getChave() + "\nPois seu fator de balancemento é: " + balancementoAtual +
                     	"\nE de seu filho à direita: " + balancementoAtualFilhoDireita);
                 return rotacionarEsquerda(atual);
             }

             // rotação simples a direita
             if (balancementoAtual > 1 &&  balancementoAtualFilhoEsquerda == 1) {
             	System.out.println("\nRotação a direita no nó: " + atual.getChave() + "\nPois seu fator de balancemento é: " + balancementoAtual +
                     	"\nE de seu filho à esquerda: " + balancementoAtualFilhoEsquerda);
                 return rotacionarDireita(atual);
             }

             // rotação dupla a esquerda
             if (balancementoAtual < -1 && balancementoAtualFilhoDireita == 1) {
             	System.out.println("\nRotação rotação dupla a esquerda no nó: " + atual.getChave() + "\nPois seu fator de balancemento é: " + balancementoAtual +
                     	"\nE de seu filho à direita: " + balancementoAtualFilhoDireita);
                 atual.setDireita(rotacionarDireita(atual.getDireita()));
                 return rotacionarEsquerda(atual);
             }

             // rotação dupla a direita
             if (balancementoAtual > 1 && balancementoAtualFilhoEsquerda == -1) {
             	System.out.println("\nRotação rotação dupla à direita no nó: " + atual.getChave() + "\nPois seu fator de balancemento é: " + balancementoAtual +
             	"\nE de seu filho à esquerda: " + balancementoAtualFilhoEsquerda);
                 atual.setEsquerda(rotacionarEsquerda(atual.getEsquerda()));
                 return rotacionarDireita(atual);
             }

             return atual;
        	
        }
        
        private int getAltura(Node node) {
            if (node == null) {
                return 0;
            }
            return node.getAltura();
        }

        private int getBalanceamento(Node node) {
            if (node == null) {
                return 0;
            }
            return getAltura(node.getEsquerda()) - getAltura(node.getDireita());
        }
}
