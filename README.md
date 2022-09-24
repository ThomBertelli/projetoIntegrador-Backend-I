<h1>Projeto Integrador - Sistema de reserva de consultas para Clínica Odontológica</h1>

<div>
<h2>Requisitos</h2>
<ul>
<li>Administração de dados odontológicos: Adicionar e modificar os dados
dos dentistas. Registrar nome, sobrenome e matrícula de cadastro.</li>

<li>Administração de pacientes: Registrar, modificar e excluir pacientes. De
cada um se armazenam: nome, sobrenome, endereço, RG, data de alta.</li>

<li>Login: Validar a entrada no sistema por meio de um login com nome de
usuário e senha. Permitir que qualquer pessoa logada registre uma
consulta, mas apenas aqueles que têm uma função de administração pode
gerenciar dentistas e pacientes.</li>

<li>Registrar consulta: Deve ser possível permitir que um paciente seja
atribuído a uma consulta com um dentista em uma determinada data e
hora.</li>
</div>

<div>
<h2>Requerimentos técnicos</h2>
<p>A aplicação deve ser desenvolvida em camadas:</p>

<li>Camada de entidade de negócios: são as classes Java do nosso negócio
modeladas através do paradigma orientado a objetos.</li>

<li>Camada de acesso a dados (Repositório): são as classes que se encarregam
de acessar o banco de dados.</li>

<li>Camada de dados (banco de dados): é o banco de dados do nosso sistema
modelado através de um modelo entidade-relacionamento. Usaremos a
base H2 por sua praticidade.</li>

<li> Camada de negócio: são as classes de serviço que se encarregam de
desacoplar o acesso aos dados da visão.</li>
</div>

<div>
<br>
<p> --- </p>
<h3>Autores</h3>
<p>Thomas Breno Bertelli</p>
<p>Dario Oh</p>
<p>Harry Möbbs Júnior</p>
<p>Marina Fachinello</p>

<br>
<p> --- </p>
<h3>Curso</h3>
<p>Disciplina de Backend I</p>
<p>Turma 07 - DH - CTD 2022</p>
<p>Certified Tech Developer</P>
</div>
