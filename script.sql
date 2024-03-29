USE [QuizOnline]
GO
ALTER TABLE [dbo].[Quiz] DROP CONSTRAINT [FK_Quiz_Subject]
GO
ALTER TABLE [dbo].[Quiz] DROP CONSTRAINT [FK_Quiz_Registration]
GO
ALTER TABLE [dbo].[Question] DROP CONSTRAINT [FK_Question_Subject]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 5/4/2021 10:54:49 PM ******/
DROP TABLE [dbo].[Subject]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 5/4/2021 10:54:49 PM ******/
DROP TABLE [dbo].[Registration]
GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 5/4/2021 10:54:49 PM ******/
DROP TABLE [dbo].[Quiz]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 5/4/2021 10:54:49 PM ******/
DROP TABLE [dbo].[Question]
GO
USE [master]
GO
/****** Object:  Database [QuizOnline]    Script Date: 5/4/2021 10:54:49 PM ******/
DROP DATABASE [QuizOnline]
GO
/****** Object:  Database [QuizOnline]    Script Date: 5/4/2021 10:54:49 PM ******/
CREATE DATABASE [QuizOnline]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuizOnline', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QuizOnline.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QuizOnline_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QuizOnline_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QuizOnline] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizOnline].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizOnline] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizOnline] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizOnline] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizOnline] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizOnline] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizOnline] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizOnline] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizOnline] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizOnline] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizOnline] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizOnline] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizOnline] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizOnline] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizOnline] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizOnline] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuizOnline] SET  MULTI_USER 
GO
ALTER DATABASE [QuizOnline] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizOnline] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuizOnline] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuizOnline] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QuizOnline] SET DELAYED_DURABILITY = DISABLED 
GO
USE [QuizOnline]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 5/4/2021 10:54:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Question](
	[QuestionID] [varchar](50) NOT NULL,
	[Title] [varchar](500) NULL,
	[Option1] [varchar](200) NULL,
	[Option2] [varchar](200) NULL,
	[Option3] [varchar](200) NULL,
	[Answer] [varchar](200) NULL,
	[DateOfCreate] [datetime] NULL,
	[SubjectID] [varchar](50) NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[QuestionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 5/4/2021 10:54:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Quiz](
	[QuizID] [varchar](50) NOT NULL,
	[Email] [varchar](50) NULL,
	[SubjectID] [varchar](50) NULL,
	[Point] [float] NULL,
	[DateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_Quiz] PRIMARY KEY CLUSTERED 
(
	[QuizID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 5/4/2021 10:54:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registration](
	[Email] [varchar](50) NOT NULL,
	[Password] [varchar](500) NULL,
	[Fullname] [varchar](50) NULL,
	[Role] [varchar](50) NULL,
	[Status] [varchar](50) NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 5/4/2021 10:54:49 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Subject](
	[SubjectID] [varchar](50) NOT NULL,
	[Name] [varchar](50) NULL,
	[Description] [varchar](200) NULL,
	[NumberOfQuestions] [int] NULL,
	[Time] [int] NULL,
 CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED 
(
	[SubjectID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-1', N'when creating your own class and you want to make it directly support sorting, which interface must it implement?', N'Comparable', N'Sortator', N'Comparator', N'Comparable', CAST(N'2021-02-18 15:20:40.860' AS DateTime), N'OOP', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-2', N'Which is NOT a basic role of Scrum team?', N'Product owner', N'Development team', N'Tester', N'Tester', CAST(N'2021-02-18 15:22:44.007' AS DateTime), N'SWE102', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-3', N'The____method returns a string containing the value of the named initialization parameter', N'init( )', N'getServletConfig( )', N'getInitParameter( )', N'getInitParameter( )', CAST(N'2021-02-18 15:23:05.130' AS DateTime), N'PRJ321', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-4', N'which of the following classes supports developers to get the pointer if a file?', N'java.io.FileStream', N'java.io.File', N'java.io.RandomAccessFile', N'java.io.RandomAccessFile', CAST(N'2021-02-19 00:00:00.000' AS DateTime), N'OOP', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-5', N'which of the following modifiers does not allow a variable to be modified its value once it was initialized?', N'private', N'final', N'static', N'final', CAST(N'2021-02-20 00:00:00.000' AS DateTime), N'OOP', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-6', N'The ability of a programming language to process objects differently depending on their type is', N'Inheritance', N'Polymorphism', N'Overriding', N'Polymorphism', CAST(N'2021-02-20 22:58:31.493' AS DateTime), N'OOP', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-7', N'which of the following may not be declared final?', N'Methods', N'Classes', N'Interfaces', N'Classes', CAST(N'2021-02-25 17:32:22.767' AS DateTime), N'OOP', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-8', N'Which of these class is used to read characters in a file?', N'FileReader', N'InputStreamReader', N'FileInputStream', N'FileReader', CAST(N'2021-02-25 17:36:08.107' AS DateTime), N'OOP', 1)
INSERT [dbo].[Question] ([QuestionID], [Title], [Option1], [Option2], [Option3], [Answer], [DateOfCreate], [SubjectID], [Status]) VALUES (N'Q-9', N'How to design a new class', N'aaaa c', N'bbb c', N'ccc c', N'bbb c', CAST(N'2021-04-26 09:11:12.457' AS DateTime), N'OOP', 0)
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-1', N'ledat170700@gmail.com', N'OOP', 10, CAST(N'2021-02-20 20:44:58.857' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-10', N'ledat170700@gmail.com', N'OOP', 8, CAST(N'2021-02-26 21:15:53.337' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-11', N'ledat170700@gmail.com', N'OOP', 8, CAST(N'2021-02-26 21:26:46.870' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-12', N'ledat170700@gmail.com', N'OOP', 6.6666665077209473, CAST(N'2021-04-22 09:26:16.687' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-13', N'ledat170700@gmail.com', N'OOP', 3.3333332538604736, CAST(N'2021-04-22 10:06:36.467' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-14', N'ledat170700@gmail.com', N'OOP', 0, CAST(N'2021-04-22 10:07:44.030' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-15', N'ledat170700@gmail.com', N'PRJ321', 0, CAST(N'2021-04-22 20:16:31.960' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-16', N'tynguyen@gmail.com', N'OOP', 0, CAST(N'2021-04-26 09:08:21.790' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-17', N'tynguyen@gmail.com', N'SWE102', 0, CAST(N'2021-04-26 09:08:52.437' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-18', N'tynguyen@gmail.com', N'OOP', 0, CAST(N'2021-04-26 09:09:24.550' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-19', N'ledat170700@gmail.com', N'OOP', 6, CAST(N'2021-04-26 09:21:54.703' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-2', N'ledat170700@gmail.com', N'OOP', 8, CAST(N'2021-02-25 17:50:30.350' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-20', N'ledat170700@gmail.com', N'OOP', 0, CAST(N'2021-04-26 09:22:20.557' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-21', N'ledat170700@gmail.com', N'OOP', 5, CAST(N'2021-05-04 22:21:49.003' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-3', N'ledat170700@gmail.com', N'OOP', 0, CAST(N'2021-02-25 17:53:58.693' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-4', N'ledat170700@gmail.com', N'OOP', 4, CAST(N'2021-02-26 08:38:09.930' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-5', N'ledat170700@gmail.com', N'OOP', 6, CAST(N'2021-02-26 08:53:29.780' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-6', N'ledat170700@gmail.com', N'OOP', 0, CAST(N'2021-02-26 19:05:33.040' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-7', N'ledat170700@gmail.com', N'OOP', 0, CAST(N'2021-02-26 19:10:47.023' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-8', N'ledat170700@gmail.com', N'OOP', 4, CAST(N'2021-02-26 19:21:58.270' AS DateTime))
INSERT [dbo].[Quiz] ([QuizID], [Email], [SubjectID], [Point], [DateOfCreate]) VALUES (N'Q-9', N'ledat170700@gmail.com', N'OOP', 8, CAST(N'2021-02-26 21:11:15.777' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Role], [Status]) VALUES (N'kythi2000@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Tran Duong Phuc An', N'admin', N'New')
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Role], [Status]) VALUES (N'ledat170700@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Le Dat', N'student', N'New')
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Role], [Status]) VALUES (N'teonguyen@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Nguyen Van Teo', N'admin', N'New')
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Role], [Status]) VALUES (N'tynguyen@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'nguyen thi ty', N'student', N'New')
INSERT [dbo].[Subject] ([SubjectID], [Name], [Description], [NumberOfQuestions], [Time]) VALUES (N'OOP', N'Object-oriented programming', N'asdas', 2, 50)
INSERT [dbo].[Subject] ([SubjectID], [Name], [Description], [NumberOfQuestions], [Time]) VALUES (N'PRJ321', N'Web-Based Java Applications', N'sdd', 5, 60)
INSERT [dbo].[Subject] ([SubjectID], [Name], [Description], [NumberOfQuestions], [Time]) VALUES (N'SWE102', N'Introduction to Software Engineering', N'sds', 5, 40)
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Subject] FOREIGN KEY([SubjectID])
REFERENCES [dbo].[Subject] ([SubjectID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Subject]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Registration] FOREIGN KEY([Email])
REFERENCES [dbo].[Registration] ([Email])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK_Quiz_Registration]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Subject] FOREIGN KEY([SubjectID])
REFERENCES [dbo].[Subject] ([SubjectID])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK_Quiz_Subject]
GO
USE [master]
GO
ALTER DATABASE [QuizOnline] SET  READ_WRITE 
GO
