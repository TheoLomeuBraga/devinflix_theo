//alinhar shift + alt + f
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.xml.crypto.Data;

import java.util.Random;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;




class generos_conteudos{
    public static String 
    pendente = "pendente",
    ação = "ação",
    comedia = "comedia",
    drana = "drana",
    terror = "terror";
}

class conteudo{
    public static enum linguagem{
        portugues_brasil,
        portugues_portugal,
        ingles,
        japones
    }

    String nome,sinops,link;
    ArrayList<String> generos;
    ArrayList<linguagem> legendas;
    ArrayList<linguagem> dublagem;
    int aprovações,reprovações,id;
}


class filme extends conteudo{
    

    


    filme(String nome){
        this.nome = nome;
        generos = new ArrayList<String>();
    }

    filme(String nome,String sinops,String link){
        this.nome = nome;
        this.sinops = sinops;
        this.link = link;
        generos = new ArrayList<String>();
    }
    

}





class serie extends conteudo{
    int ebisodios;
    serie(int ebisodios){this.ebisodios = ebisodios;}
}










class conta_bancaria{
    conta_bancaria(){}

    static public float preco_mes;

    public String banco,numero_cartao; 
    public float saldo,credito;
    

    public Date dia_pagamento;

    public void renovar(float valor){
         if(preco_mes >= credito){
            credito -= preco_mes;
            dia_pagamento = new Date();
         }
    }

    public boolean pagamento_em_dia(){
        boolean ret = false;
        return ret;
    }

    public float pegar_valor_saldo(){
        float ret = 0;
        saldo = ret;
        return ret;
    }
}

class pedido_filme{
    public pedido_filme(){}
    public Date momento_pedido;
    public String filme_solicitado;
    
    public pedido_filme(Date momento_pedido,String filme_solicitado ){
        this.momento_pedido = momento_pedido;
        this.filme_solicitado = filme_solicitado;
    }
    
}

class usuario{
    String nome;
    Date data_nascimento;
    usuario(String nome,Date data_nascimento){
        this.nome = nome;
        this.data_nascimento = data_nascimento;
    }
    ArrayList<filme> filmes_curtidos;

}

class conta{
    conta(){}
    public static enum Genero{
        masculino,
        feminino,
        nao_declarado
    }

    ArrayList<usuario> usuarios;

    String nome;
    String nome_completo;
    String e_mail,senha;
    conta_bancaria conta;
    Genero genero = Genero.nao_declarado;
    String genero_filme_preferido = generos_conteudos.pendente;
    Date data_nascimento;
    String pais,estado,cidade;
    ArrayList<Integer> filmes_assistidos = new ArrayList<Integer>(),filmes_aprovados = new ArrayList<Integer>(),recomendações = new ArrayList<Integer>();

    pedido_filme Pedido_filme;

    conta(String nome){
        this.nome = nome;
    }
    conta(String nome,Date data_nascimento){
        this.nome = nome;
        this.data_nascimento = data_nascimento;
    }

    public void adicionar Usuario (usuario u){
        if(usuarios.size() < 3){
            usuarios.add(u);
        }
    }
    

}

class servidor{
    static int mais_curtido;
    static ArrayList<filme> filmes = new ArrayList<filme>();
    static ArrayList<conta> contas = new ArrayList<conta>();
    static ArrayList<Integer> em_alta = new ArrayList<Integer>();
    static Map<String,Integer> generos_curtidos;
    static ArrayList<String> filmes_pedidos;
    
    public static void iniciar(){
        servidor.adicionar_filme(new filme("NULL"));
        generos_curtidos.put(generos_conteudos.pendente,0);
        generos_curtidos.put(generos_conteudos.ação,1);
        generos_curtidos.put(generos_conteudos.comedia,2);
        generos_curtidos.put(generos_conteudos.drana,3);
        generos_curtidos.put(generos_conteudos.terror,4);
    }

    public static int buscar_mais_curtido(){
        filmes.get(0).aprovações = 0;
        int ret = 0;
        for(int i = 0;i < filmes.size();i++){
            if(filmes.get(i).aprovações > filmes.get(ret).aprovações){
                ret = i;
            }
        }
        System.out.println(filmes.get(ret).nome + " é o filme mais curtido");
        return ret;
    }

    public static int adicionar_filme(filme Filme){
        filmes.add(Filme);
        Filme.id = filmes.size();
        return filmes.size() -1;
    }
    public static int adicionar_conta(conta conta){
        contas.add(conta);
        return contas.size() -1;
    }

    public static filme pegar_filme(int ID){return filmes.get(ID);}
    public static conta pegar_conta(int ID){return contas.get(ID);}

    public static ArrayList<filme> pegar_filmes(int ID){return filmes;}
    public static ArrayList<conta> pegar_contas(int ID){return contas;}

    public static int pegar_id_filme_nome(String nome){
        int ret = 0;
        for(int i = 0; i < filmes.size();i++){
            if(nome.equals(filmes.get(i).nome)){
                ret = i;
                break;
            }
        }
        return ret;
    }
    public static void curtir_filme(int id){
        filmes.get(id).aprovações++;
        for(int i = 0;i < filmes.get(id).generos.size();i++){
            int a = generos_curtidos.get(filmes.get(id).generos.get(i)) ;
            a +=1;
            generos_curtidos.put(filmes.get(id).generos.get(i), a);
        }

        //generos_curtidos
    }
    public static String pegar_genero_mais_curtido(){
        String genero = "";
        int visualizacoes = 0;
        for(var i : generos_curtidos.entrySet()){
            if(i.getValue() > visualizacoes){
                visualizacoes = i.getValue();
                genero = i.getKey();
            }
        }
        return genero;
    }

    public static void adicionar_catalogo_teste(){
        servidor.adicionar_filme(new filme("Filme A"));
        servidor.adicionar_filme(new filme("Filme B"));
        servidor.adicionar_filme(new filme("Filme C"));
        servidor.adicionar_filme(new filme("Teste"));
        servidor.adicionar_filme(new filme("Teste 2"));
        servidor.adicionar_filme(new filme("Teste 3"));

        conta Theo = new conta("Théo");
        ArrayList<Integer> lista = new ArrayList<Integer>();
        lista.add(0);
        lista.add(1);
        lista.add(2);
        Theo.filmes_assistidos = lista;
        servidor.adicionar_conta(Theo);
        servidor.adicionar_conta(new conta("Leonardo"));
        servidor.adicionar_conta(new conta("Danilo"));
        servidor.adicionar_conta(new conta("Migel"));
        servidor.adicionar_conta(new conta("Bruno"));
        servidor.adicionar_conta(new conta("gustavo"));

        servidor.adicionar_conta(new conta("andre"));
        servidor.adicionar_conta(new conta("pedrinho"));
        servidor.adicionar_conta(new conta("daniel"));
        servidor.adicionar_conta(new conta("alfredo"));
        servidor.adicionar_conta(new conta("adam"));

        servidor.adicionar_conta(new conta("amanda"));
        servidor.adicionar_conta(new conta("ana"));
        servidor.adicionar_conta(new conta("bibi"));
        servidor.adicionar_conta(new conta("fifi"));
        servidor.adicionar_conta(new conta("lili"));

        local.id_conta = 0;

    }

    public static void prdir_filme(String filme){
        boolean filme_ja_pedido = false;
        for(String s : filmes_pedidos){
            if(s.equals(filme)){
                filme_ja_pedido = true;
                break;
            }
        }
        if(!filme_ja_pedido){
            filmes_pedidos.add(filme);
        }
    }

}

class local{
    static int id_conta;

    static void pedir_filme(String filme_solicitado){
        servidor.contas.get(id_conta).Pedido_filme = new pedido_filme(new Date(),filme_solicitado);
    }

    static void curtir_aleatorio (){
        conta usu = servidor.contas.get(id_conta);
        //pega filme aleatorio 
        Random gerador = new Random();
        int felme_asistido_aleatorio = gerador.nextInt(usu.filmes_assistidos.size());
        int filme_id = usu.filmes_assistidos.get(felme_asistido_aleatorio);
        String nome_filme = servidor.filmes.get(filme_id).nome;
        System.out.println("voce curtiu o filme " + servidor.pegar_filme(felme_asistido_aleatorio).nome + " y\\n" );
        Scanner scanner = new Scanner(System.in);
        String _string = scanner.nextLine();
        if(_string.compareTo("y") == 0){
            System.out.println("voce curtiu o filme");
            System.out.println("obrigado pelo seu tempo " + usu.nome);
            servidor.curtir_filme(filme_id);
            servidor.contas.get(id_conta).filmes_aprovados.add(filme_id);
        }else{
            System.out.println("voce não curtiu o filme");
        }

    }
}


public class DevInFlix {
    
    
    public static void main(String args[]) {
        
        servidor.iniciar();
        servidor.adicionar_catalogo_teste();
        local.curtir_aleatorio();
        servidor.buscar_mais_curtido();
    }
}
