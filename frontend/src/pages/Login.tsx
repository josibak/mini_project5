
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { BookOpen, Eye, EyeOff, Home } from 'lucide-react';
import { Button } from '@/components/ui/button';

const Login = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });
  const navigate = useNavigate();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // 로그인 로직 구현
    alert('로그인 되었습니다!');
    navigate('/');
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-warm-brown-50 to-warm-brown-100 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <div className="text-center mb-8">
          <div className="flex items-center justify-between mb-6">
            <Link to="/" className="inline-flex items-center gap-2 text-warm-brown-700 hover:text-warm-brown-800">
              <Home className="w-5 h-5" />
              <span className="text-sm">홈으로</span>
            </Link>
            <Link to="/" className="inline-flex items-center gap-2">
              <BookOpen className="w-8 h-8 text-warm-brown-700" />
              <span className="text-2xl font-bold text-warm-brown-800">StoryBloom</span>
            </Link>
            <div className="w-16"></div>
          </div>
          <h1 className="text-3xl font-light text-gray-900 mb-2">로그인</h1>
          <p className="text-gray-600">계정에 로그인하여 다양한 이야기를 만나보세요</p>
        </div>

        <div className="bg-white rounded-2xl shadow-lg border border-warm-brown-200 p-8">
          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">
                이메일
              </label>
              <input
                type="email"
                id="email"
                value={formData.email}
                onChange={(e) => setFormData({...formData, email: e.target.value})}
                className="w-full px-4 py-3 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                placeholder="이메일을 입력하세요"
                required
              />
            </div>

            <div>
              <label htmlFor="password" className="block text-sm font-medium text-gray-700 mb-2">
                비밀번호
              </label>
              <div className="relative">
                <input
                  type={showPassword ? 'text' : 'password'}
                  id="password"
                  value={formData.password}
                  onChange={(e) => setFormData({...formData, password: e.target.value})}
                  className="w-full px-4 py-3 pr-12 border border-warm-brown-300 rounded-lg focus:ring-2 focus:ring-warm-brown-500 focus:border-transparent"
                  placeholder="비밀번호를 입력하세요"
                  required
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-500 hover:text-gray-700"
                >
                  {showPassword ? <EyeOff className="w-5 h-5" /> : <Eye className="w-5 h-5" />}
                </button>
              </div>
            </div>

            <div className="flex items-center justify-between">
              <label className="flex items-center">
                <input type="checkbox" className="rounded border-warm-brown-300 text-warm-brown-600 focus:ring-warm-brown-500" />
                <span className="ml-2 text-sm text-gray-600">로그인 상태 유지</span>
              </label>
              <Link to="/forgot-password" className="text-sm text-warm-brown-600 hover:text-warm-brown-700">
                비밀번호 찾기
              </Link>
            </div>

            <Button
              type="submit"
              className="w-full bg-warm-brown-700 hover:bg-warm-brown-800 text-white py-3 text-lg font-medium rounded-lg"
            >
              로그인
            </Button>
          </form>

          <div className="mt-6 text-center">
            <p className="text-gray-600">
              계정이 없으신가요?{' '}
              <Link to="/signup" className="text-warm-brown-600 hover:text-warm-brown-700 font-medium">
                회원가입
              </Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
